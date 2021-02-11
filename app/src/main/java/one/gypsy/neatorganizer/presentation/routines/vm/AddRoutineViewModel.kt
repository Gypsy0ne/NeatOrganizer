package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks
import one.gypsy.neatorganizer.domain.routines.AddRoutine
import one.gypsy.neatorganizer.domain.routines.AddRoutineSchedule

class AddRoutineViewModel(
    private val addRoutineUseCase: one.gypsy.neatorganizer.domain.routines.AddRoutine,
    private val addRoutineSchedule: one.gypsy.neatorganizer.domain.routines.AddRoutineSchedule
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
            one.gypsy.neatorganizer.domain.routines.AddRoutine.Params(
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
            one.gypsy.neatorganizer.domain.routines.AddRoutineSchedule.Params(
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
