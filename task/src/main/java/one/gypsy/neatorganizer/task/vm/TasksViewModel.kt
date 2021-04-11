package one.gypsy.neatorganizer.task.vm

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.core.listing.ContentLoadingStatus
import one.gypsy.neatorganizer.core.listing.updateLoadingStatus
import one.gypsy.neatorganizer.core.utils.extensions.delayItemsEmission
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroups
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroupWithTasks
import one.gypsy.neatorganizer.task.model.TaskListItem
import one.gypsy.neatorganizer.task.model.TaskListMapper
import one.gypsy.neatorganizer.task.model.toSingleTask
import one.gypsy.neatorganizer.task.model.toSingleTaskGroup

internal class TasksViewModel(
    getAllSingleTaskGroupsUseCase: GetAllSingleTaskGroups,
    private val updateSingleTaskGroupUseCase: UpdateSingleTaskGroupWithTasks,
    private val updateSingleTaskUseCase: UpdateSingleTask,
    private val removeSingleTaskUseCase: RemoveSingleTask,
    private val taskListMapper: TaskListMapper
) : ViewModel() {

    private val _listedTasks = MediatorLiveData<List<TaskListItem>>()
    val listedTasks: LiveData<List<TaskListItem>> = _listedTasks.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val listedItems = viewModelScope.async { taskListMapper.getVisibleItems(it) }
            _contentLoadingStatus.updateLoadingStatus(it)
            emit(listedItems.await())
        }
    }
    private val _contentLoadingStatus =
        MutableLiveData<ContentLoadingStatus>(ContentLoadingStatus.ContentLoading)
    val contentLoadingStatus: LiveData<ContentLoadingStatus> = _contentLoadingStatus

    init {
        getAllSingleTaskGroupsUseCase.invoke(viewModelScope, Unit) {
            it.either(
                onFailure = { _contentLoadingStatus.updateLoadingStatus(emptyList<SingleTaskGroupWithTasksDto>()) },
                onSuccess = ::onGetAllGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun onGetAllGroupsWithSingleTasksSuccess(taskGroups: LiveData<List<SingleTaskGroupWithTasksDto>>) =
        _listedTasks.addSource(taskGroups) {
            viewModelScope.launch {
                val mappedTasks = viewModelScope.async {
                    taskListMapper.mapTasksToListItems(
                        it,
                        _listedTasks.value.orEmpty()
                    )
                }
                delayItemsEmission(it.size)
                _listedTasks.postValue(mappedTasks.await())
            }
        }

    fun onExpand(headerItem: TaskListItem.TaskListHeader) =
        _listedTasks.postValue(taskListMapper.updateExpansion(headerItem.id, _listedTasks.value))

    fun onHeaderUpdate(headerItem: TaskListItem.TaskListHeader) =
        updateSingleTaskGroupUseCase.invoke(
            viewModelScope,
            UpdateSingleTaskGroupWithTasks.Params(singleTaskGroupWithTasks = headerItem.toSingleTaskGroup())
        )

    fun onTaskUpdate(subItem: TaskListItem.TaskListSubItem) = updateSingleTaskUseCase.invoke(
        viewModelScope,
        UpdateSingleTask.Params(singleTask = subItem.toSingleTask())
    )

    fun onRemove(subItem: TaskListItem.TaskListSubItem) = removeSingleTaskUseCase.invoke(
        viewModelScope,
        RemoveSingleTask.Params(subItem.toSingleTask())
    )
}
