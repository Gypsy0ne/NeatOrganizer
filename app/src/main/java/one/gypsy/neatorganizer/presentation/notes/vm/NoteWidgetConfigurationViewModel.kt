package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.dto.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.notes.GetAllNoteEntries
import one.gypsy.neatorganizer.domain.interactors.notes.widget.SaveNoteWidget
import one.gypsy.neatorganizer.presentation.notes.model.NoteEntryItem
import one.gypsy.neatorganizer.presentation.notes.model.toNoteEntryItem

class NoteWidgetConfigurationViewModel(
    getAllNoteEntriesUseCase: GetAllNoteEntries,
    private val saveNoteWidgetUseCase: SaveNoteWidget
) : ViewModel() {

    private val _widgetCreationStatus = MutableLiveData<NoteWidgetCreationStatus>()
    val widgetCreationStatus: LiveData<NoteWidgetCreationStatus> = _widgetCreationStatus

    private val _listedNotes: MediatorLiveData<List<NoteEntryItem>> = MediatorLiveData()
    val listedNotes: LiveData<List<NoteEntryItem>> = _listedNotes

    private val _selectedNote = MutableLiveData<NoteEntryItem>()
    val selectedNote: LiveData<NoteEntryItem> = _selectedNote

    init {
        getAllNoteEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                {},
                ::onGetAllNoteEntriesSuccess
            )
        }
    }

    private fun onGetAllNoteEntriesSuccess(noteEntriesObservable: LiveData<List<NoteEntry>>) {
        _listedNotes.addSource(noteEntriesObservable) {
            viewModelScope.launch {
                _listedNotes.postValue(it.map { it.toNoteEntryItem() })
            }
        }
    }

    fun onNoteSelected(note: NoteEntryItem) {
        if (note != selectedNote.value) {
            _selectedNote.postValue(note)
        }
    }

    fun saveNoteWidget(widgetId: Int) =
        selectedNote.value?.let {
            saveNoteWidgetUseCase.invoke(
                viewModelScope,
                SaveNoteWidget.Params(
                    NoteWidgetEntry(
                        widgetId = widgetId,
                        noteId = it.id,
                        color = it.color
                    )
                )
            ) { result ->
                result.either(
                    {},
                    { _widgetCreationStatus.postValue(NoteWidgetCreationStatus.CreationSuccessStatus) }
                )
            }
        } ?: _widgetCreationStatus.postValue(NoteWidgetCreationStatus.NoteNotSelectedStatus)
}

sealed class NoteWidgetCreationStatus {
    object NoteNotSelectedStatus : NoteWidgetCreationStatus()
    object CreationSuccessStatus : NoteWidgetCreationStatus()
}
