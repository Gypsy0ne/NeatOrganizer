package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks
import one.gypsy.neatorganizer.domain.interactors.Failure
import one.gypsy.neatorganizer.domain.interactors.routines.*
import one.gypsy.neatorganizer.presentation.common.ContentLoadingStatus
import one.gypsy.neatorganizer.presentation.common.updateLoadingStatus
import one.gypsy.neatorganizer.presentation.routines.model.*
import one.gypsy.neatorganizer.utils.extensions.delayItemsEmission

class RoutinesViewModel(
    getAllRoutinesUseCase: one.gypsy.neatorganizer.domain.routines.GetAllRoutines,
    private val updateRoutine: one.gypsy.neatorganizer.domain.routines.UpdateRoutine,
    private val removeRoutineTask: one.gypsy.neatorganizer.domain.routines.RemoveRoutineTask,
    private val updateRoutineTask: one.gypsy.neatorganizer.domain.routines.UpdateRoutineTask,
    private val routineListMapper: RoutineListMapper,
    private val updateRoutineSchedule: one.gypsy.neatorganizer.domain.routines.UpdateRoutineSchedule
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

    private fun onGetAllRoutinesSuccess(routines: LiveData<List<RoutineWithTasks>>) {
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

    private fun onGetAllRoutinesFailure(failure: one.gypsy.neatorganizer.domain.interactors.Failure) =
        _contentLoadingStatus.updateLoadingStatus(emptyList<RoutineListItem>())

    fun onHeaderUpdate(routineHeaderItem: RoutineListItem.RoutineListHeader) {
        viewModelScope.launch {
            updateRoutine.invoke(
                this,
                one.gypsy.neatorganizer.domain.routines.UpdateRoutine.Params(routine = routineHeaderItem.toRoutine())
            ) {
                it.either(
                    {},
                    {
                        updateRoutineSchedule.invoke(
                            this,
                            one.gypsy.neatorganizer.domain.routines.UpdateRoutineSchedule.Params(
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
            one.gypsy.neatorganizer.domain.routines.UpdateRoutineTask.Params(routineTask = routineSubItem.toRoutineTask())
        )
    }

    fun onRemove(routineSubItem: RoutineListItem.RoutineListSubItem) {
        removeRoutineTask.invoke(
            viewModelScope,
            one.gypsy.neatorganizer.domain.routines.RemoveRoutineTask.Params(routineTask = routineSubItem.toRoutineTask())
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
