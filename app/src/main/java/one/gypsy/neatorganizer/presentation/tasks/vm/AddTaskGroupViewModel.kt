package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.AddTaskGroup
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class AddTaskGroupViewModel @Inject constructor(var addTaskGroupUseCase: AddTaskGroup): ViewModel() {

    val taskGroupTitle = MutableLiveData<String>()

    fun addTaskGroup() {
        addTaskGroupUseCase.invoke(viewModelScope, AddTaskGroup.Params(SingleTaskGroup(taskGroupTitle.value ?: ""))) {
            it.either(::onAddSingleTaskGroupFailure, ::onAddSingleTaskGroupSuccess)
        }
    }

    fun onAddSingleTaskGroupSuccess(newTaskGroupId: Long) {

    }

    fun onAddSingleTaskGroupFailure(failure: Failure) {

    }

}