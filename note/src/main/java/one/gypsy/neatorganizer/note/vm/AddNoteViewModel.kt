package one.gypsy.neatorganizer.note.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntryDto
import one.gypsy.neatorganizer.domain.interactors.notes.InsertNoteEntry

internal class AddNoteViewModel(private val insertNoteEntryUseCase: InsertNoteEntry) : ViewModel() {

    val noteTitle = MutableLiveData<String>()
    private var pickedColor: Int? = null

    private val _noteCreationStatus = MutableLiveData<NoteCreationStatus>()
    val noteCreationStatus: LiveData<NoteCreationStatus> = _noteCreationStatus

    fun onSubmitClicked() = if (pickedColor == null) {
        _noteCreationStatus.postValue(NoteCreationStatus.ColorNotPickedStatus)
    } else {
        addNote()
    }

    private fun addNote() {
        pickedColor?.let { noteColor ->
            insertNoteEntryUseCase.invoke(
                viewModelScope,
                InsertNoteEntry.Params(
                    NoteEntryDto(
                        title = noteTitle.value.orEmpty(),
                        createdAt = System.currentTimeMillis(),
                        color = noteColor
                    )
                )
            ) {
                it.either(
                    onSuccess = { _noteCreationStatus.postValue(NoteCreationStatus.CreationSuccessStatus) }
                )
            }
        }
    }

    fun onColorPicked(color: Int) {
        pickedColor = color
    }
}

sealed class NoteCreationStatus {
    object ColorNotPickedStatus : NoteCreationStatus()
    object CreationSuccessStatus : NoteCreationStatus()
}
