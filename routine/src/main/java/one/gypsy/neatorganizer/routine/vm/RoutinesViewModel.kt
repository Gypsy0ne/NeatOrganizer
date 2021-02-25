package one.gypsy.neatorganizer.routine.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.core.listing.ContentLoadingStatus
import one.gypsy.neatorganizer.core.listing.updateLoadingStatus
import one.gypsy.neatorganizer.core.utils.extensions.delayItemsEmission
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.routines.GetAllRoutines
import one.gypsy.neatorganizer.domain.interactors.routines.RemoveRoutineTask
import one.gypsy.neatorganizer.domain.interactors.routines.UpdateRoutine
import one.gypsy.neatorganizer.domain.interactors.routines.UpdateRoutineSchedule
import one.gypsy.neatorganizer.domain.interactors.routines.UpdateRoutineTask
import one.gypsy.neatorganizer.routine.model.RoutineListItem
import one.gypsy.neatorganizer.routine.model.RoutineListMapper
import one.gypsy.neatorganizer.routine.model.getRoutineSchedule
import one.gypsy.neatorganizer.routine.model.toRoutine
import one.gypsy.neatorganizer.routine.model.toRoutineTask

internal class RoutinesViewModel(
    getAllRoutinesUseCase: GetAllRoutines,
    private val updateRoutine: UpdateRoutine,
    private val removeRoutineTask: RemoveRoutineTask,
    private val updateRoutineTask: UpdateRoutineTask,
    private val routineListMapper: RoutineListMapper,
    private val updateRoutineSchedule: UpdateRoutineSchedule
) : ViewModel() {

    private val _listedRoutines = MediatorLiveData<List<RoutineListItem>>()
    val listedRoutines: LiveData<List<RoutineListItem>> = _listedRoutines.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val listedItems = viewModelScope.async { routineListMapper.getVisibleItems(it) }
            _contentLoadingStatus.updateLoadingStatus(it)
            emit(listedItems.await())
        }
    }
    private val _contentLoadingStatus =
        MutableLiveData<ContentLoadingStatus>(ContentLoadingStatus.ContentLoading)
    val contentLoadingStatus: LiveData<ContentLoadingStatus> = _contentLoadingStatus

    init {
        getAllRoutinesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllRoutinesFailure,
                ::onGetAllRoutinesSuccess
            )
        }
    }

    private fun onGetAllRoutinesSuccess(routines: LiveData<List<RoutineWithTasksDto>>) {
        _listedRoutines.addSource(routines) {
            viewModelScope.launch {
                val mappedRoutines = viewModelScope.async {
                    routineListMapper.mapRoutinesToListItems(
                        it,
                        _listedRoutines.value.orEmpty()
                    )
                }
                delayItemsEmission(it.size)
                _listedRoutines.postValue(mappedRoutines.await())
            }
        }
    }

    private fun onGetAllRoutinesFailure(failure: Failure) =
        _contentLoadingStatus.updateLoadingStatus(emptyList<RoutineListItem>())

    fun onHeaderUpdate(routineHeaderItem: RoutineListItem.RoutineListHeader) {
        viewModelScope.launch {
            updateRoutine.invoke(
                this,
                UpdateRoutine.Params(routine = routineHeaderItem.toRoutine())
            ) {
                it.either(
                    {},
                    {
                        updateRoutineSchedule.invoke(
                            this,
                            UpdateRoutineSchedule.Params(
                                routineSchedule = routineHeaderItem.getRoutineSchedule()
                            )
                        )
                    }
                )
            }
        }
    }

    fun onTaskUpdate(routineSubItem: RoutineListItem.RoutineListSubItem) {
        updateRoutineTask.invoke(
            viewModelScope,
            UpdateRoutineTask.Params(routineTask = routineSubItem.toRoutineTask())
        )
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
