package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveTaskGroupById
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.extensions.default

class RemoveTaskGroupViewModel(val removeTaskGroupByIdUseCase: RemoveTaskGroupById) :
    ViewModel() {

    private val _actionFinished = MutableLiveData<Boolean>().default(false)
    val actionFinished: LiveData<Boolean>
        get() = _actionFinished

    fun onRemoveSubmit(removedItemId: Long) {
        removeTaskGroupByIdUseCase.invoke(
            viewModelScope,
            RemoveTaskGroupById.Params(removedItemId)
        ) {
            it.either(::onRemoveFailure, ::onRemoveSuccess)
        }
    }

    private fun onRemoveSuccess(unit: Unit) {
        _actionFinished.postValue(true)
    }

    private fun onRemoveFailure(failure: Failure) {}
}