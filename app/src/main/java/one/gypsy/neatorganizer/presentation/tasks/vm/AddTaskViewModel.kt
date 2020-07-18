package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.AddSingleTask
import one.gypsy.neatorganizer.utils.Failure

class AddTaskViewModel(
    var addSingleTaskUseCase: AddSingleTask, val groupId: Long
) : ViewModel() {
    val taskTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    fun addTask() {
        addSingleTaskUseCase.invoke(
            viewModelScope,
            AddSingleTask.Params(
                SingleTaskEntry(
                    groupId = groupId,
                    name = taskTitle.value.orEmpty(),
                    done = false
                )
            )
        ) {
            it.either(::onAddTaskFailure, ::onAddTaskSuccess)
        }
    }

    fun addNextTask() {
        addSingleTaskUseCase.invoke(
            viewModelScope,
            AddSingleTask.Params(
                SingleTaskEntry(
                    groupId = groupId,
                    name = taskTitle.value.orEmpty(),
                    done = false
                )
            )
        ) {
            it.either(::onAddNextTaskFailure, ::onAddNextTaskSuccess)
        }
    }


    private fun onAddTaskSuccess(newTaskId: Long) {
        _finishedAdding.postValue(true)
    }

    private fun onAddTaskFailure(failure: Failure) {

    }

    private fun onAddNextTaskSuccess(newTaskId: Long) {
        taskTitle.postValue("")
    }

    private fun onAddNextTaskFailure(failure: Failure) {

    }
}