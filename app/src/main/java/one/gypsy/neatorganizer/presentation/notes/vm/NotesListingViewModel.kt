package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.interactors.notes.GetAllNoteEntries
import one.gypsy.neatorganizer.presentation.common.ContentLoadingStatus
import one.gypsy.neatorganizer.presentation.common.updateLoadingStatus
import one.gypsy.neatorganizer.presentation.notes.model.NoteEntryItem
import one.gypsy.neatorganizer.presentation.notes.model.toNoteEntryItem
import one.gypsy.neatorganizer.utils.extensions.delayItemsEmission

class NotesListingViewModel(
    getAllNoteEntriesUseCase: GetAllNoteEntries,
) : ViewModel() {

    private val _listedNotes: MediatorLiveData<List<NoteEntryItem>> = MediatorLiveData()
    val listedNotes: LiveData<List<NoteEntryItem>> = _listedNotes

    private val _contentLoadingStatus =
        MutableLiveData<ContentLoadingStatus>(ContentLoadingStatus.ContentLoading)
    val contentLoadingStatus: LiveData<ContentLoadingStatus> = _contentLoadingStatus

    init {
        getAllNoteEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                { _contentLoadingStatus.updateLoadingStatus(emptyList<NoteEntryItem>()) },
                ::onGetAllNoteEntriesSuccess
            )
        }
    }

    private fun onGetAllNoteEntriesSuccess(noteEntriesObservable: LiveData<List<NoteEntry>>) {
        _listedNotes.addSource(noteEntriesObservable) {
            viewModelScope.launch {
                val mappedEntries = viewModelScope.async {
                    it.map { it.toNoteEntryItem() }
                }
                delayItemsEmission(it.size)
                mappedEntries.await().let {
                    _listedNotes.postValue(it)
                    _contentLoadingStatus.updateLoadingStatus(it)
                }
            }
        }
    }
}
