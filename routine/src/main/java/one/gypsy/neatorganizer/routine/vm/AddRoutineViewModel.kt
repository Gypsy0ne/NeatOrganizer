package one.gypsy.neatorganizer.routine.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutineSchedule

class AddRoutineViewModel(
    private val addRoutineUseCase: AddRoutine,
    private val addRoutineSchedule: AddRoutineSchedule
) : ViewModel() {

    val routineTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean> = _finishedAdding

    private var scheduledDays: List<Boolean> = List(7) {
        false
    }

    fun addRoutine() {
        addRoutineUseCase.invoke(
            viewModelScope,
            AddRoutine.Params(
                RoutineWithTasks(
                    name = routineTitle.value.orEmpty(),
                    schedule = RoutineSchedule.EMPTY,
                    tasks = emptyList(),
                    createdAt = System.currentTimeMillis()
                )
            )
        ) {
            it.either({}, ::onAddRoutineSuccess)
        }
    }

    fun onAddRoutineSuccess(newRoutineId: Long) {
        addRoutineSchedule.invoke(
            viewModelScope,
            AddRoutineSchedule.Params(
                RoutineSchedule(
                    routineId = newRoutineId,
                    scheduledDays = scheduledDays
                )
            )
        ) {
            it.either({}, { _finishedAdding.postValue(true) })
        }
    }

    fun onScheduleChanged(updatedScheduleDays: List<Boolean>) {
        scheduledDays = updatedScheduleDays
    }
}
