package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.notes.InsertNoteEntry

class AddNoteViewModel(private val insertNoteEntryUseCase: InsertNoteEntry) : ViewModel() {

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
                    NoteEntry(
                        title = noteTitle.value.orEmpty(),
                        createdAt = System.currentTimeMillis(),
                        color = noteColor
                    )
                )
            ) {
                it.either(
                    {},
                    { _noteCreationStatus.postValue(NoteCreationStatus.CreationSuccessStatus) }
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
