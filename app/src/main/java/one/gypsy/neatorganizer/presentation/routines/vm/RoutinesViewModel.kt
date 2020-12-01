package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.interactors.routines.*
import one.gypsy.neatorganizer.presentation.common.ContentLoadingStatus
import one.gypsy.neatorganizer.presentation.routines.model.*
import one.gypsy.neatorganizer.utils.Failure

class RoutinesViewModel(
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
            if (it.isEmpty()) {
                _contentLoadingStatus.postValue(ContentLoadingStatus.ContentEmpty)
            } else {
                _contentLoadingStatus.postValue(ContentLoadingStatus.ContentLoaded)
            }
            emit(routineListMapper.getVisibleItems(it))
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

    private fun onGetAllRoutinesSuccess(routines: LiveData<List<Routine>>) {
        _listedRoutines.addSource(routines) {
            viewModelScope.launch {
                _listedRoutines.postValue(
                    routineListMapper.mapRoutinesToListItems(
                        it,
                        _listedRoutines.value ?: emptyList()
                    )
                )
            }
        }
    }

    private fun onGetAllRoutinesFailure(failure: Failure) {}

    fun onHeaderUpdate(routineHeaderItem: RoutineListItem.RoutineListHeader) {
        viewModelScope.launch {
            updateRoutine.invoke(
                this,
                UpdateRoutine.Params(routine = routineHeaderItem.toRoutine())
            ) {
                it.either(
                    {
                        // nop
                    },
                    {
                        updateRoutineSchedule.invoke(
                            this,
                            UpdateRoutineSchedule.Params(routineSchedule = routineHeaderItem.getRoutineSchedule())
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
