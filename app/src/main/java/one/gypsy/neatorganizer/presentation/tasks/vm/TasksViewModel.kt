package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.*
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.presentation.tasks.model.changeVisibility
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    var getAllSingleTaskGroupsUseCase: GetAllSingleTaskGroups,
    var updateSingleTaskGroupUseCase: UpdateTaskGroup,
    var updateSingleSingleTaskUseCase: UpdateSingleTask,
    var removeSingleTaskGroupUseCase: RemoveTaskGroup,
    var removeSingleSingleTaskUseCase: RemoveSingleTask
) :
    ViewModel() {

    val taskListMapper = TaskListMapper()
    private val allTasks = MediatorLiveData<List<SingleTaskGroup>>()
    private val _listedTasks =
        MediatorLiveData<List<TaskListItem>>().apply {
            addSource(allTasks) { taskGroups ->
                postValue(mapToVisibleListItems(taskGroups))
            }
        }
    val listedTasks: LiveData<List<TaskListItem>> = Transformations.map(_listedTasks) { tasks ->
        tasks.filter { it.visible }
    }

    init {
        getAllSingleTaskGroupsUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllGroupsWithSingleTasksFailure,
                ::onGetAllGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun mapToVisibleListItems(taskGroups: List<SingleTaskGroup>): List<TaskListItem> {
        return taskListMapper.flattenTaskGroupsToList(
            taskGroups,
            getExpandedItemsIds() ?: emptyList()
        )
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

    private fun findTask(taskId: Long, taskGroupId: Long) =
        findTaskGroup(taskGroupId)
            ?.tasks?.find { it.id == taskId }

    private fun findTaskGroup(groupId: Long) =
        allTasks.value?.find { it.id == groupId }


    fun onExpand(headerItem: TaskListItem.TaskListHeader) {
        _listedTasks.postValue(_listedTasks.value?.map {
            mapTaskListItem(it, headerItem)
        })
    }

    private fun mapTaskListItem(
        mappedItem: TaskListItem,
        headerItem: TaskListItem.TaskListHeader
    ): TaskListItem {
        return when {
            isHeader(mappedItem, headerItem) -> {
                headerItem
            }
            isSubItem(mappedItem, headerItem) -> {
                mappedItem.changeVisibility(headerItem.expanded)
            }
            else -> {
                mappedItem
            }
        }
    }

    private fun isSubItem(
        it: TaskListItem,
        headerItem: TaskListItem.TaskListHeader
    ) = it is TaskListItem.TaskListSubItem && it.groupId == headerItem.id

    private fun isHeader(
        it: TaskListItem,
        headerItem: TaskListItem.TaskListHeader
    ) = it is TaskListItem.TaskListHeader && it.id == headerItem.id

    fun onEditionSubmit(headerItem: TaskListItem.TaskListHeader) {
        findTaskGroup(headerItem.id)?.also {
            updateSingleTaskGroupUseCase.invoke(
                viewModelScope,
                UpdateTaskGroup.Params(it.copy(name = headerItem.name))
            ) { result ->
                result.either(
                    ::onUpdateSingleTaskGroupFailure,
                    ::onUpdateSingleTaskGroupSuccess
                )
            }
        }
    }

    private fun onUpdateSingleTaskGroupSuccess(unit: Unit) {

    }


    private fun onUpdateSingleTaskGroupFailure(failure: Failure) {

    }

    fun onEditionSubmit(subItem: TaskListItem.TaskListSubItem) {
        findTask(subItem.id, subItem.groupId)?.also {
            updateSingleSingleTaskUseCase.invoke(
                viewModelScope,
                UpdateSingleTask.Params(it.copy(name = subItem.name))
            ) { result ->
                result.either(
                    ::onUpdateSingleTaskFailure,
                    ::onUpdateSingleTaskSuccess
                )
            }
        }
    }

    private fun onUpdateSingleTaskSuccess(unit: Unit) {

    }

    private fun onUpdateSingleTaskFailure(failure: Failure) {

    }

    fun onTaskDone(subItem: TaskListItem.TaskListSubItem) {
        findTask(subItem.id, subItem.groupId)?.also {
            updateSingleSingleTaskUseCase.invoke(
                viewModelScope,
                UpdateSingleTask.Params(it.copy(done = subItem.done))
            ) { result ->
                result.either(
                    ::onSingleTaskStatusUpdateFailure,
                    ::onSingleTaskStatusUpdateSuccess
                )
            }
        }
    }

    private fun onSingleTaskStatusUpdateSuccess(unit: Unit) {

    }

    private fun onSingleTaskStatusUpdateFailure(failure: Failure) {

    }

    fun onRemove(headerItem: TaskListItem.TaskListHeader) {
        findTaskGroup(headerItem.id)?.also {
            removeSingleTaskGroupUseCase.invoke(
                viewModelScope,
                RemoveTaskGroup.Params(it)
            ) { result ->
                result.either(
                    ::onUpdateSingleTaskFailure,
                    ::onUpdateSingleTaskSuccess
                )
            }
        }
    }

    private fun onRemoveSingleTaskGroupSuccess(unit: Unit) {

    }

    private fun onRemoveSingleTaskGroupFailure(failure: Failure) {

    }

    fun onRemove(subItem: TaskListItem.TaskListSubItem) {
        findTask(subItem.id, subItem.groupId)?.also {
            removeSingleSingleTaskUseCase.invoke(
                viewModelScope,
                RemoveSingleTask.Params(it)
            ) { result ->
                result.either(
                    ::onUpdateSingleTaskFailure,
                    ::onUpdateSingleTaskSuccess
                )
            }
        }
    }

    private fun onRemoveSingleTaskSuccess(unit: Unit) {

    }

    private fun onRemoveSingleTaskFailure(failure: Failure) {

    }
}