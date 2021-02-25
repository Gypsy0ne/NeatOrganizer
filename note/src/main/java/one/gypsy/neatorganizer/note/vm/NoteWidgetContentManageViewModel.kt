package one.gypsy.neatorganizer.note.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.notes.NoteDto
import one.gypsy.neatorganizer.domain.interactors.notes.GetNoteById
import one.gypsy.neatorganizer.domain.interactors.notes.UpdateNote
import one.gypsy.neatorganizer.note.model.NoteItem
import one.gypsy.neatorganizer.note.model.toDto
import one.gypsy.neatorganizer.note.model.toNoteItem

internal class NoteWidgetContentManageViewModel(
    noteId: Long,
    private val getNoteByIdUseCase: GetNoteById,
    private val updateNoteUseCase: UpdateNote
) : ViewModel() {

    private val _note = MediatorLiveData<NoteItem>()
    val note: LiveData<NoteItem> = _note

    private val _edited = MutableLiveData(false)
    val edited: LiveData<Boolean> = _edited

    private val _dataLoadingStatus = MutableLiveData<NoteManageLoadingStatus>()
    val dataLoadingStatus: LiveData<NoteManageLoadingStatus> = _dataLoadingStatus

    init {
        loadNoteData(noteId)
    }

    private fun onGetNoteByIdSuccess(noteObservable: LiveData<NoteDto>) {
        _note.addSource(noteObservable) {
            _note.postValue(it.toNoteItem())
        }
        _dataLoadingStatus.postValue(NoteManageLoadingStatus.Success)
    }

    fun onEditIconClicked() = _edited.value?.let { editionEnabled ->
        _edited.postValue(!editionEnabled)
    }

    fun onEditionFinish(title: String, content: String) = note.value?.let {
        updateNoteUseCase.invoke(
            viewModelScope,
            UpdateNote.Params(it.copy(title = title, content = content).toDto())
        )
    }

    fun loadNoteData(noteId: Long) = getNoteByIdUseCase.invoke(
        viewModelScope,
        GetNoteById.Params(noteId)
    ) {
        it.either(
            { _dataLoadingStatus.postValue(NoteManageLoadingStatus.Error) },
            ::onGetNoteByIdSuccess
        )
    }
}

sealed class NoteManageLoadingStatus {
    object Error : NoteManageLoadingStatus()
    object Success : NoteManageLoadingStatus()
}
