package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.routines.AddRoutineTask

class AddRoutineTaskViewModel(
    private val addRoutineTask: one.gypsy.neatorganizer.domain.routines.AddRoutineTask,
    private val routineId: Long
) : ViewModel() {

    val taskTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean> = _finishedAdding

    fun addRoutineTask() = add({}, { _finishedAdding.postValue(true) })

    fun addNextRoutineTask() = add({}, { taskTitle.postValue("") })

    private fun add(
        onFailure: (one.gypsy.neatorganizer.domain.interactors.Failure) -> Any,
        onSuccess: (Unit) -> Any
    ) {
        addRoutineTask.invoke(
            viewModelScope,
            one.gypsy.neatorganizer.domain.routines.AddRoutineTask.Params(
                RoutineTaskEntry(
                    routineId = routineId,
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
