package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.interactors.routines.*
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.presentation.routines.model.toRoutine
import one.gypsy.neatorganizer.presentation.routines.model.toRoutineTask
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class RoutinesViewModel @Inject constructor(
    val getAllRoutinesUseCase: GetAllRoutines,
    val removeRoutine: RemoveRoutine,
    val updateRoutine: UpdateRoutine,
    val removeRoutineTask: RemoveRoutineTask,
    val updateRoutineTask: UpdateRoutineTask,
    val routineListMapper: RoutineListMapper
) : ViewModel() {
    private val _listedRoutines = MediatorLiveData<List<RoutineListItem>>()
    val listedRoutines: LiveData<List<RoutineListItem>> =
        Transformations.map(_listedRoutines) { routineItems ->
            routineListMapper.getVisibleItems(routineItems)
        }

    init {
        getAllRoutinesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllRoutineFailure,
                ::onGetAllRoutineSuccess
            )
        }
    }


    private fun onGetAllRoutineSuccess(routines: LiveData<List<Routine>>) {
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

    private fun onGetAllRoutineFailure(failure: Failure) {

    }

    fun onEditionSubmit(routineHeaderItem: RoutineListItem.RoutineListHeader) {
        updateRoutine.invoke(
            viewModelScope,
            UpdateRoutine.Params(routine = routineHeaderItem.toRoutine())
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
        _listedRoutines.postValue(_listedRoutines.value?.map {
            negateExpandedIfHeader(it, headerItem.id)
        })
    }

    private fun negateExpandedIfHeader(
        it: RoutineListItem,
        headerItemId: Long
    ) = if (it is RoutineListItem.RoutineListHeader && it.id == headerItemId) {
        it.copy(expanded = !it.expanded)
    } else {
        it
    }

}