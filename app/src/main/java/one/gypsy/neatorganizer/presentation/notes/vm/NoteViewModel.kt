package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.interactors.notes.GetNoteById
import one.gypsy.neatorganizer.domain.interactors.notes.UpdateNote
import one.gypsy.neatorganizer.presentation.notes.model.NoteItem
import one.gypsy.neatorganizer.presentation.notes.model.toNote
import one.gypsy.neatorganizer.presentation.notes.model.toNoteItem

class NoteViewModel(
    noteId: Long,
    getNoteByIdUseCase: GetNoteById,
    private val updateNoteUseCase: UpdateNote
) : ViewModel() {

    private val _note = MediatorLiveData<NoteItem>()
    val note: LiveData<NoteItem> = _note

    private val _edited = MutableLiveData(false)
    val edited: LiveData<Boolean> = _edited

    init {
        getNoteByIdUseCase.invoke(
            viewModelScope,
            GetNoteById.Params(noteId)
        ) { it.either({}, ::onGetNoteByIdSuccess) }
    }

    private fun onGetNoteByIdSuccess(noteObservable: LiveData<Note>) {
        _note.addSource(noteObservable) {
            _note.postValue(it.toNoteItem())
        }
    }

    fun onEditIconClicked() = _edited.value?.let { editionEnabled ->
        _edited.postValue(!editionEnabled)
    }

    fun onEditionFinish(title: String, content: String) = note.value?.let {
        updateNoteUseCase.invoke(
            viewModelScope,
            UpdateNote.Params(it.copy(title = title, content = content).toNote())
        )
    }
}
