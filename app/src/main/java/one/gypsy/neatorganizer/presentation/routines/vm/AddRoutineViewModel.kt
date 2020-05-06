package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutineSchedule
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class AddRoutineViewModel @Inject constructor(
    val addRoutineUseCase: AddRoutine,
    val addRoutineSchedule: AddRoutineSchedule
) : ViewModel() {

    val routineTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    private var scheduledDays: List<Boolean> = List(7) {
        false
    }

    fun addRoutine() {
        addRoutineUseCase.invoke(
            viewModelScope,
            AddRoutine.Params(
                Routine(
                    name = routineTitle.value.orEmpty(),
                    schedule = RoutineSchedule()
                )
            )
        ) {
            it.either(::onAddRoutineFailure, ::onAddRoutineSuccess)
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
            it.either(::onAddRoutineScheduleFailure, ::onAddRoutineScheduleSuccess)
        }
    }

    fun onAddRoutineFailure(failure: Failure) {

    }

    fun onAddRoutineScheduleSuccess(newRoutineScheduleId: Long) {
        _finishedAdding.postValue(true)
    }

    fun onAddRoutineScheduleFailure(failure: Failure) {

    }

    fun onScheduleChanged(updatedScheduleDays: List<Boolean>) {
        scheduledDays = updatedScheduleDays
    }
}