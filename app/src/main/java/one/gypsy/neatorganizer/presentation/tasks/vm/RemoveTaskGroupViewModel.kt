package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.presentation.common.RemoveViewModel
import one.gypsy.neatorganizer.utils.extensions.default
import javax.inject.Inject

class RemoveTaskGroupViewModel @Inject constructor(val removeTaskGroupByIdUseCase: RemoveTaskGroupById) :
    ViewModel(), RemoveViewModel {

    private val _actionFinished = MutableLiveData<Boolean>().default(false)
    override val actionFinished: LiveData<Boolean>
        get() = _actionFinished

    override fun onRemoveSubmit(removedItemId: Long) {
        removeTaskGroupByIdUseCase.invoke(viewModelScope, RemoveTaskGroupById.Params(removedItemId))
    }
}