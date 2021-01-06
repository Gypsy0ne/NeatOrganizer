package one.gypsy.neatorganizer.presentation.notes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.interactors.notes.DeleteNoteById
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.extensions.default

class DeleteNoteViewModel(private val deleteNoteByIdUseCase: DeleteNoteById) : ViewModel() {

    private val _actionFinished = MutableLiveData<Boolean>().default(false)
    val actionFinished: LiveData<Boolean> = _actionFinished

    fun onRemoveSubmit(removedItemId: Long) {
        deleteNoteByIdUseCase.invoke(
            viewModelScope,
            DeleteNoteById.Params(removedItemId)
        ) {
            it.either(::onRemoveFailure, ::onRemoveSuccess)
        }
    }

    private fun onRemoveSuccess(unit: Unit) {
        _actionFinished.postValue(true)
    }

    private fun onRemoveFailure(failure: Failure) {}
}
