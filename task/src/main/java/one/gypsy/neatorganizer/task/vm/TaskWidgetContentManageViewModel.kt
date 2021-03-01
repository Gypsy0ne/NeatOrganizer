package one.gypsy.neatorganizer.task.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntryDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupWithTasksById
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup
import one.gypsy.neatorganizer.task.model.TaskListItem
import one.gypsy.neatorganizer.task.model.toSingleTask
import one.gypsy.neatorganizer.task.model.toTaskListSubItem

internal class TaskWidgetContentManageViewModel(
    taskGroupId: Long,
    private val getSingleTaskGroupWithTasksUseCase: GetSingleTaskGroupWithTasksById,
    private val updateSingleTaskUseCase: UpdateSingleTask,
    private val removeSingleTaskUseCase: RemoveSingleTask,
    private val updateTaskGroupUseCase: UpdateSingleTaskGroup
) : ViewModel() {

    // TODO it should use UI model
    private val _taskGroup = MediatorLiveData<SingleTaskGroupDto>()
    val taskGroup: LiveData<SingleTaskGroupDto> = _taskGroup

    private val _listedTasks = MediatorLiveData<List<SingleTaskEntryDto>>()
    val listedTasks: LiveData<List<TaskListItem.TaskListSubItem>> =
        _listedTasks.distinctUntilChanged().switchMap {
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                emit(it.map { it.toTaskListSubItem() }.sortedByDescending { it.createdAt })
            }
        }

    private val _titleEdited = MutableLiveData(false)
    val titleEdited: LiveData<Boolean> = _titleEdited

    private val _widgetDataLoaded = MutableLiveData<TaskWidgetDataLoadingStatus>()
    val widgetDataLoaded: LiveData<TaskWidgetDataLoadingStatus> = _widgetDataLoaded

    init {
        loadTaskGroupWithTasks(taskGroupId)
    }

    private fun onGetAllSingleTasksSuccess(taskGroupWithTasks: LiveData<SingleTaskGroupWithTasksDto>) {
        _listedTasks.addSource(taskGroupWithTasks) {
            _listedTasks.postValue(taskGroupWithTasks.value?.tasks)
        }
        _taskGroup.addSource(taskGroupWithTasks) {
            _taskGroup.postValue(taskGroupWithTasks.value?.taskGroup)
        }
        _widgetDataLoaded.postValue(TaskWidgetDataLoadingStatus.LoadingSuccess)
    }

    fun onTitleEditionFinished(editedTitle: String) {
        taskGroup.value?.let { taskGroup ->
            updateTaskGroupUseCase.invoke(
                viewModelScope,
                UpdateSingleTaskGroup.Params(
                    taskGroup.copy(
                        name = editedTitle,
                        id = taskGroup.id,
                        createdAt = taskGroup.createdAt
                    )
                )
            ) {
                it.either({}, {})
            }
        }
    }

    fun onTaskUpdate(taskItem: TaskListItem.TaskListSubItem) = updateSingleTaskUseCase.invoke(
        viewModelScope,
        UpdateSingleTask.Params(singleTask = taskItem.toSingleTask())
    )

    fun onRemove(taskItem: TaskListItem.TaskListSubItem) = removeSingleTaskUseCase.invoke(
        viewModelScope,
        RemoveSingleTask.Params(taskItem.toSingleTask())
    )

    fun onEditIconClicked() = _titleEdited.value?.let { editionEnabled ->
        _titleEdited.postValue(!editionEnabled)
    }

    fun loadTaskGroupWithTasks(taskGroupId: Long) = getSingleTaskGroupWithTasksUseCase.invoke(
        viewModelScope,
        GetSingleTaskGroupWithTasksById.Params(taskGroupId)
    ) {
        it.either(
            {
                _widgetDataLoaded.postValue(TaskWidgetDataLoadingStatus.LoadingError)
            },
            ::onGetAllSingleTasksSuccess
        )
    }
}

sealed class TaskWidgetDataLoadingStatus {
    object LoadingError : TaskWidgetDataLoadingStatus()
    object LoadingSuccess : TaskWidgetDataLoadingStatus()
}
