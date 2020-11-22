package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.AddSingleTask

class AddTaskViewModel(
    private val addSingleTaskUseCase: AddSingleTask, val groupId: Long
) : ViewModel() {

    val taskTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean> = _finishedAdding

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
            it.either({}, ::onAddTaskSuccess)
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
            it.either({}, ::onAddNextTaskSuccess)
        }
    }

    private fun onAddTaskSuccess(newTaskId: Long) = _finishedAdding.postValue(true)

    private fun onAddNextTaskSuccess(newTaskId: Long) = taskTitle.postValue("")
}