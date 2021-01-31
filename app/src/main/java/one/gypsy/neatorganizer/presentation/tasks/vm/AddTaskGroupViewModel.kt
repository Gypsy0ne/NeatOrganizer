package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.AddTaskGroup

class AddTaskGroupViewModel(private val addTaskGroupUseCase: AddTaskGroup) : ViewModel() {

    val taskGroupTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean> = _finishedAdding

    fun addTaskGroup() {
        addTaskGroupUseCase.invoke(
            viewModelScope,
            AddTaskGroup.Params(
                SingleTaskGroup(
                    name = taskGroupTitle.value.orEmpty(),
                    createdAt = System.currentTimeMillis()
                )
            )
        ) {
            it.either({}, { _finishedAdding.postValue(true) })
        }
    }
}
