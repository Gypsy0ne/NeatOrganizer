package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.*
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTask
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTaskGroup
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    var getAllSingleTaskGroupsUseCase: GetAllSingleTaskGroups,
    var updateSingleTaskGroupUseCase: UpdateTaskGroup,
    var updateSingleTaskUseCase: UpdateSingleTask,
    var removeSingleTaskGroupUseCase: RemoveTaskGroup,
    var removeSingleSingleTaskUseCase: RemoveSingleTask,
    var taskListMapper: TaskListMapper
) : ViewModel() {
    private val _listedTasks = MediatorLiveData<List<TaskListItem>>()
    val listedTasks: LiveData<List<TaskListItem>> =
        Transformations.map(_listedTasks) { taskItems ->
            taskListMapper.getVisibleItems(taskItems)
        }

    init {
        getAllSingleTaskGroupsUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllGroupsWithSingleTasksFailure,
                ::onGetAllGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun onGetAllGroupsWithSingleTasksSuccess(taskGroups: LiveData<List<SingleTaskGroup>>) {
        with(_listedTasks) {
            addSource(taskGroups) { taskGroups ->
                this.postValue(
                    taskListMapper.mapTasksToListItems(
                        taskGroups,
                        this.value ?: emptyList()
                    )
                )
            }
        }
    }

    private fun onGetAllGroupsWithSingleTasksFailure(failure: Failure) {}

    fun onExpand(headerItem: TaskListItem.TaskListHeader) {
        _listedTasks.postValue(taskListMapper.updateExpansion(headerItem.id, _listedTasks.value))
    }

    fun onHeaderUpdate(headerItem: TaskListItem.TaskListHeader) {
        updateSingleTaskGroupUseCase.invoke(
            viewModelScope,
            UpdateTaskGroup.Params(singleTaskGroup = headerItem.toSingleTaskGroup())
        )
    }

    fun onTaskUpdate(subItem: TaskListItem.TaskListSubItem) {
        updateSingleTaskUseCase.invoke(
            viewModelScope,
            UpdateSingleTask.Params(singleTask = subItem.toSingleTask())
        )
    }

    fun onRemove(headerItem: TaskListItem.TaskListHeader) {
        removeSingleTaskGroupUseCase.invoke(
            viewModelScope,
            RemoveTaskGroup.Params(headerItem.toSingleTaskGroup())
        )
    }

    fun onRemove(subItem: TaskListItem.TaskListSubItem) {
        removeSingleSingleTaskUseCase.invoke(
            viewModelScope,
            RemoveSingleTask.Params(subItem.toSingleTask())
        )
    }
}