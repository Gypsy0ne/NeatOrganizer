package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry
import one.gypsy.neatorganizer.domain.interactors.notes.InsertNoteEntry

class AddNoteViewModel(private val insertNoteEntryUseCase: InsertNoteEntry) : ViewModel() {

    val noteTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    fun addNote() {
        insertNoteEntryUseCase.invoke(
            viewModelScope,
            InsertNoteEntry.Params(
                NoteEntry(title = noteTitle.value.orEmpty(), createdAt = System.currentTimeMillis())
            )
        ) {
            it.either({}, ::onAddNoteSuccess)
        }
    }

    fun onAddNoteSuccess(newNoteId: Long) =
        _finishedAdding.postValue(true)
}
