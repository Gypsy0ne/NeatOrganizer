package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.AddTask
import one.gypsy.neatorganizer.utils.Failure

class AddTaskViewModel @AssistedInject constructor(
    var addTaskUseCase: AddTask,
    @Assisted val groupId: Long
) : ViewModel() {
    val taskTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    fun addTask() {
        addTaskUseCase.invoke(
            viewModelScope,
            AddTask.Params(
                SingleTaskEntry(
                    groupId = groupId,
                    name = taskTitle.value ?: "",
                    done = false
                )
            )
        ) {
            it.either(::onAddTaskFailure, ::onAddTaskSuccess)
        }
    }

    fun addNextTask() {
        addTaskUseCase.invoke(
            viewModelScope,
            AddTask.Params(
                SingleTaskEntry(
                    groupId = groupId,
                    name = taskTitle.value ?: "",
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

    @AssistedInject.Factory
    interface Factory {
        fun create(groupId: Long): AddTaskViewModel
    }
}