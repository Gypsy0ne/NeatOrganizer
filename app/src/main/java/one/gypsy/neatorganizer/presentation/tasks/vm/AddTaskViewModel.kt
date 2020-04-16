package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.AddTask
import one.gypsy.neatorganizer.utils.Failure

class AddTaskViewModel @AssistedInject constructor(
    var addTaskUseCase: AddTask,
    @Assisted val groupId: Long
) : ViewModel() {
    val taskTitle = MutableLiveData<String>()

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

    fun onAddTaskSuccess(newTaskId: Long) {
        println("Dodano $newTaskId")
    }

    fun onAddTaskFailure(failure: Failure) {

    }

    @AssistedInject.Factory
    interface Factory {
        fun create(groupId: Long): AddTaskViewModel
    }
}