package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.GetAllGroupsWithSingleTasks
import one.gypsy.neatorganizer.domain.interactors.task.RemoveTask
import one.gypsy.neatorganizer.domain.interactors.task.RemoveTaskGroup
import one.gypsy.neatorganizer.domain.interactors.task.UpdateTask
import one.gypsy.neatorganizer.domain.interactors.task.UpdateTaskGroup
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    var getAllGroupsWithSingleTasksUseCase: GetAllGroupsWithSingleTasks,
    var updateSingleTaskGroupUseCase: UpdateTaskGroup,
    var updateSingleTaskUseCase: UpdateTask,
    var removeSingleTaskGroupUseCase: RemoveTaskGroup,
    var removeSingleTaskUseCase: RemoveTask
) :
    ViewModel() {

    val taskListMapper = TaskListMapper()
    private val allTasks = MediatorLiveData<List<SingleTaskGroup>>()
    private val _listedTasks =
        MediatorLiveData<List<TaskListItem>>().apply {
            addSource(allTasks) { taskGroups ->
                postValue(
                    taskListMapper.flattenTaskGroupsToList(
                        taskGroups,
                        getExpandedItemsIds() ?: emptyList()
                    )
                )
            }
        }
    val listedTasks: LiveData<List<TaskListItem>> = Transformations.map(_listedTasks) { tasks ->
        tasks.filter { it.visible }
    }

    init {
        _listedTasks
        getAllGroupsWithSingleTasksUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllGroupsWithSingleTasksFailure,
                ::onGetAllGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun getExpandedItemsIds(): List<Long>? =
        _listedTasks.value
            ?.filter { (it is TaskListItem.TaskListHeader && it.expanded) }
            ?.map { it.id }

    private fun onGetAllGroupsWithSingleTasksSuccess(groupsWithTasksCollection: LiveData<List<SingleTaskGroup>>) {
        allTasks.addSource(groupsWithTasksCollection) {
            allTasks.postValue(it)
        }
    }

    private fun onGetAllGroupsWithSingleTasksFailure(failure: Failure) {

    }

    fun onExpand(headerItem: TaskListItem.TaskListHeader) {
        _listedTasks.postValue(_listedTasks.value?.map {
            if (it is TaskListItem.TaskListSubItem && it.groupId == headerItem.groupId) {
                it.copy(visible = headerItem.expanded)
            } else {
                it
            }
        })
    }

    fun onEditionSubmit(headerItem: TaskListItem.TaskListHeader) {
        val updatedTaskGroup =
            allTasks.value?.find { it.id == headerItem.id }?.copy(name = headerItem.name)
        if (updatedTaskGroup != null) {
            updateSingleTaskGroupUseCase.invoke(
                viewModelScope,
                UpdateTaskGroup.Params(updatedTaskGroup)
            ) {
                it.either(
                    ::onUpdateSingleTaskGroupFailure,
                    ::onUpdateSingleTaskGroupSuccess
                )
            }
        } else {
            // TODO handle case
        }
    }

    private fun onUpdateSingleTaskGroupSuccess(unit: Unit) {

    }


    private fun onUpdateSingleTaskGroupFailure(failure: Failure) {

    }

    fun onEditionSubmit(subItem: TaskListItem.TaskListSubItem) {
        val updatedTask =
            allTasks.value?.find { it.id == subItem.groupId }
                ?.tasks?.find { it.id == subItem.id }
                ?.copy(name = subItem.name)
        if (updatedTask != null) {
            updateSingleTaskUseCase.invoke(
                viewModelScope,
                UpdateTask.Params(updatedTask)
            ) {
                it.either(
                    ::onUpdateSingleTaskFailure,
                    ::onUpdateSingleTaskSuccess
                )
            }
        } else {
            // TODO handle case
        }
    }

    private fun onUpdateSingleTaskSuccess(unit: Unit) {

    }

    private fun onUpdateSingleTaskFailure(failure: Failure) {

    }

    fun onTaskDone(subItem: TaskListItem.TaskListSubItem) {
        val updatedTask =
            allTasks.value?.find { it.id == subItem.groupId }
                ?.tasks?.find { it.id == subItem.id }
                ?.copy(done = subItem.done)
        if (updatedTask != null) {
            updateSingleTaskUseCase.invoke(
                viewModelScope,
                UpdateTask.Params(updatedTask)
            ) {
                it.either(
                    ::onSingleTaskStatusUpdateFailure,
                    ::onSingleTaskStatusUpdateSuccess
                )
            }
        } else {
            // TODO handle case
        }
    }

    private fun onSingleTaskStatusUpdateSuccess(unit: Unit) {

    }

    private fun onSingleTaskStatusUpdateFailure(failure: Failure) {

    }

    fun onRemove(headerItem: TaskListItem.TaskListHeader) {
        val removedGroup =
            allTasks.value?.find { it.id == headerItem.groupId }
        if (removedGroup != null) {
            removeSingleTaskGroupUseCase.invoke(
                viewModelScope,
                RemoveTaskGroup.Params(removedGroup)
            ) {
                it.either(
                    ::onUpdateSingleTaskFailure,
                    ::onUpdateSingleTaskSuccess
                )
            }
        } else {
            // TODO handle case
        }
    }

    private fun onRemoveSingleTaskGroupSuccess(unit: Unit) {

    }

    private fun onRemoveSingleTaskGroupFailure(failure: Failure) {

    }

    fun onRemove(subItem: TaskListItem.TaskListSubItem) {
        val removedTask = allTasks.value
            ?.find { it.id == subItem.groupId }
            ?.tasks?.find { it.id == subItem.id }
        if (removedTask != null) {
            removeSingleTaskUseCase.invoke(
                viewModelScope,
                RemoveTask.Params(removedTask)
            ) {
                it.either(
                    ::onUpdateSingleTaskFailure,
                    ::onUpdateSingleTaskSuccess
                )
            }
        } else {
            // TODO handle case
        }
    }

    private fun onRemoveSingleTaskSuccess(unit: Unit) {

    }

    private fun onRemoveSingleTaskFailure(failure: Failure) {

    }


}