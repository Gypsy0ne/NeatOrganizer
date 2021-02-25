package one.gypsy.neatorganizer.task.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntryDto
import one.gypsy.neatorganizer.domain.interactors.tasks.AddSingleTask

class AddTaskViewModel(
    private val addSingleTaskUseCase: AddSingleTask,
    private val groupId: Long
) : ViewModel() {

    val taskTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean> = _finishedAdding

    fun addTask() = add({}, { _finishedAdding.postValue(true) })

    fun addNextTask() = add({}, { taskTitle.postValue("") })

    fun add(
        onFailure: (one.gypsy.neatorganizer.domain.interactors.Failure) -> Any,
        onSuccess: (Unit) -> Any
    ) {
        addSingleTaskUseCase.invoke(
            viewModelScope,
            AddSingleTask.Params(
                SingleTaskEntryDto(
                    groupId = groupId,
                    name = taskTitle.value.orEmpty(),
                    done = false,
                    createdAt = System.currentTimeMillis()
                )
            )
        ) {
            it.either(onFailure, onSuccess)
        }
    }
}
