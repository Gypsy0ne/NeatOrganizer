package one.gypsy.neatorganizer.routine.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.routines.RoutineScheduleDto
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutineSchedule

internal class AddRoutineViewModel(
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
                RoutineWithTasksDto(
                    name = routineTitle.value.orEmpty(),
                    schedule = RoutineScheduleDto.EMPTY,
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
                RoutineScheduleDto(
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
