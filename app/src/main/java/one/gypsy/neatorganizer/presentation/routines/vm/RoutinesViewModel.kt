package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.interactors.routines.*
import one.gypsy.neatorganizer.presentation.routines.model.*
import one.gypsy.neatorganizer.utils.Failure

class RoutinesViewModel(
    getAllRoutinesUseCase: GetAllRoutines,
    private val removeRoutine: RemoveRoutine,
    private val updateRoutine: UpdateRoutine,
    private val removeRoutineTask: RemoveRoutineTask,
    private val updateRoutineTask: UpdateRoutineTask,
    private val routineListMapper: RoutineListMapper,
    private val updateRoutineSchedule: UpdateRoutineSchedule
) : ViewModel() {

    private val _listedRoutines = MediatorLiveData<List<RoutineListItem>>()
    val listedRoutines: LiveData<List<RoutineListItem>> =
        Transformations.map(_listedRoutines) { routineItems ->
            routineListMapper.getVisibleItems(routineItems)
        }

    init {
        getAllRoutinesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllRoutinesFailure,
                ::onGetAllRoutinesSuccess
            )
        }
    }

    private fun onGetAllRoutinesSuccess(routines: LiveData<List<Routine>>) {
        with(_listedRoutines) {
            addSource(routines) { routines ->
                this.postValue(
                    routineListMapper.mapRoutinesToListItems(
                        routines,
                        this.value ?: emptyList()
                    )
                )
            }
        }

    }

    private fun onGetAllRoutinesFailure(failure: Failure) {

    }

    //TODO add chain invoke
    fun onHeaderUpdate(routineHeaderItem: RoutineListItem.RoutineListHeader) {
        updateRoutine.invoke(
            viewModelScope,
            UpdateRoutine.Params(routine = routineHeaderItem.toRoutine())
        )
        updateRoutineSchedule.invoke(
            viewModelScope,
            UpdateRoutineSchedule.Params(routineSchedule = routineHeaderItem.getRoutineSchedule())
        )
    }

    fun onTaskUpdate(routineSubItem: RoutineListItem.RoutineListSubItem) {
        updateRoutineTask.invoke(
            viewModelScope,
            UpdateRoutineTask.Params(routineTask = routineSubItem.toRoutineTask())
        )
    }

    fun onRemove(routineHeaderItem: RoutineListItem.RoutineListHeader) {
        removeRoutine.invoke(viewModelScope, RemoveRoutine.Params(routineHeaderItem.toRoutine()))
    }

    fun onRemove(routineSubItem: RoutineListItem.RoutineListSubItem) {
        removeRoutineTask.invoke(
            viewModelScope,
            RemoveRoutineTask.Params(routineTask = routineSubItem.toRoutineTask())
        )
    }

    fun onExpand(headerItem: RoutineListItem.RoutineListHeader) {
        _listedRoutines.postValue(
            routineListMapper.updateExpansion(
                headerItem.id,
                _listedRoutines.value
            )
        )
    }

}