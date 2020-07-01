package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.interactors.routines.RemoveRoutineById
import one.gypsy.neatorganizer.presentation.common.RemoveViewModel
import one.gypsy.neatorganizer.utils.extensions.default
import javax.inject.Inject

class RemoveRoutineViewModel @Inject constructor(val removeRoutineByIdUseCase: RemoveRoutineById) :
    ViewModel(), RemoveViewModel {

    private val _actionFinished = MutableLiveData<Boolean>().default(false)
    override val actionFinished: LiveData<Boolean>
        get() = _actionFinished

    override fun onRemoveSubmit(removedItemId: Long) {
        removeRoutineByIdUseCase.invoke(viewModelScope, RemoveRoutineById.Params(removedItemId))
    }
}