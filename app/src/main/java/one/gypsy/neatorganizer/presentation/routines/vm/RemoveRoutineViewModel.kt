package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.routines.RemoveRoutineById
import one.gypsy.neatorganizer.presentation.common.RemoveViewModel
import one.gypsy.neatorganizer.utils.extensions.default

class RemoveRoutineViewModel(private val removeRoutineByIdUseCase: one.gypsy.neatorganizer.domain.routines.RemoveRoutineById) :
    ViewModel(), RemoveViewModel {

    private val _actionFinished = MutableLiveData<Boolean>().default(false)
    override val actionFinished: LiveData<Boolean>
        get() = _actionFinished

    override fun onRemoveSubmit(removedItemId: Long) {
        removeRoutineByIdUseCase.invoke(
            viewModelScope,
            one.gypsy.neatorganizer.domain.routines.RemoveRoutineById.Params(removedItemId)
        ) {
            it.either(::onRemoveFailure, ::onRemoveSuccess)
        }
    }

    private fun onRemoveSuccess(unit: Unit) {
        _actionFinished.postValue(true)
    }

    private fun onRemoveFailure(failure: one.gypsy.neatorganizer.domain.interactors.Failure) {}
}
