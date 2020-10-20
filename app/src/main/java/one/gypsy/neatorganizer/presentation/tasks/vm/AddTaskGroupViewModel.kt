package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.interactors.tasks.AddTaskGroup
import one.gypsy.neatorganizer.utils.Failure

class AddTaskGroupViewModel(private val addTaskGroupUseCase: AddTaskGroup) : ViewModel() {

    val taskGroupTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    fun addTaskGroup() {
        addTaskGroupUseCase.invoke(
            viewModelScope,
            AddTaskGroup.Params(
                SingleTaskGroupWithTasks(
                    taskGroupTitle.value.orEmpty()
                )
            )
        ) {
            it.either(::onAddSingleTaskGroupFailure, ::onAddSingleTaskGroupSuccess)
        }
    }

    fun onAddSingleTaskGroupSuccess(newTaskGroupId: Long) {
        _finishedAdding.postValue(true)
    }

    fun onAddSingleTaskGroupFailure(failure: Failure) {

    }
}