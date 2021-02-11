package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.tasks.GetSingleTaskGroupWithTasksById
import one.gypsy.neatorganizer.domain.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.tasks.UpdateSingleTaskGroup
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTask

class TaskWidgetContentManageViewModel(
    taskGroupId: Long,
    private val getSingleTaskGroupWithTasksUseCase: GetSingleTaskGroupWithTasksById,
    private val updateSingleTaskUseCase: UpdateSingleTask,
    private val removeSingleTaskUseCase: RemoveSingleTask,
    private val updateTaskGroupUseCase: UpdateSingleTaskGroup
) : ViewModel() {

    private val _taskGroup = MediatorLiveData<SingleTaskGroup>()
    val taskGroup: LiveData<SingleTaskGroup> = _taskGroup

    private val _listedTasks = MediatorLiveData<List<SingleTaskEntry>>()
    val listedTasks: LiveData<List<TaskListItem.TaskListSubItem>> =
        _listedTasks.distinctUntilChanged().switchMap {
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                emit(it.map { it.toTaskListSubItem() })
            }
        }

    private val _titleEdited = MutableLiveData(false)
    val titleEdited: LiveData<Boolean> = _titleEdited

    private val _widgetDataLoaded = MutableLiveData<TaskWidgetDataLoadingStatus>()
    val widgetDataLoaded: LiveData<TaskWidgetDataLoadingStatus> = _widgetDataLoaded

    init {
        loadTaskGroupWithTasks(taskGroupId)
    }

    private fun onGetAllSingleTasksSuccess(taskGroupWithTasks: LiveData<SingleTaskGroupWithTasks>) {
        _listedTasks.addSource(taskGroupWithTasks) {
            _listedTasks.postValue(taskGroupWithTasks.value.tasks)
        }
        _taskGroup.addSource(taskGroupWithTasks) {
            _taskGroup.postValue(taskGroupWithTasks.value.taskGroup)
        }
        _widgetDataLoaded.postValue(TaskWidgetDataLoadingStatus.LoadingSuccess)
    }

    fun onTitleEditionFinished(editedTitle: String) {
        taskGroup.value.let { taskGroup ->
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
