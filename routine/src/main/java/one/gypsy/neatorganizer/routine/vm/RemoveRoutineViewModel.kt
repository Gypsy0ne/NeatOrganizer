package one.gypsy.neatorganizer.routine.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.core.utils.extensions.default
import one.gypsy.neatorganizer.domain.interactors.routines.RemoveRoutineById

internal class RemoveRoutineViewModel(
    private val removeRoutineByIdUseCase: RemoveRoutineById
) : ViewModel() {

    private val _actionFinished = MutableLiveData<Boolean>().default(false)
    val actionFinished: LiveData<Boolean>
        get() = _actionFinished

    fun onRemoveSubmit(removedItemId: Long) {
        removeRoutineByIdUseCase.invoke(
            viewModelScope,
            RemoveRoutineById.Params(removedItemId)
        ) {
            it.either(::onRemoveFailure, ::onRemoveSuccess)
        }
    }

    private fun onRemoveSuccess(unit: Unit) {
        _actionFinished.postValue(true)
    }

    private fun onRemoveFailure(failure: one.gypsy.neatorganizer.domain.interactors.Failure) {}
}
