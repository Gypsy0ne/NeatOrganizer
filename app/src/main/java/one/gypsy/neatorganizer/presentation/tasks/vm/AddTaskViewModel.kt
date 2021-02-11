package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.tasks.AddSingleTask

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
                SingleTaskEntry(
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
