package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.GetAllGroupsWithSingleTasks
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskListHeader
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskListSubItem
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class TasksViewModel @Inject constructor(var getAllGroupsWithSingleTasksUseCase: GetAllGroupsWithSingleTasks) :
    ViewModel() {

    private val allTasks = MediatorLiveData<List<SingleTaskGroup>>()
    private val _listedTasks =
        MediatorLiveData<List<TaskListItem>>().apply {
            addSource(allTasks) { taskGroups ->
//                _listedTasks.value.
                postValue(flattenTaskGroupsToList(taskGroups))
            }
        }

    val listedTasks: LiveData<List<TaskListItem>> = Transformations.map(_listedTasks) { tasks ->
        tasks.filter { it.visible }
    }
    //To keep database updated automatically update livedata from use case

    init {
        _listedTasks
        getAllGroupsWithSingleTasksUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllGroupsWithSingleTasksFailure,
                ::onGetAllGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun onGetAllGroupsWithSingleTasksSuccess(groupsWithTasksCollection: LiveData<List<SingleTaskGroup>>) {
        allTasks.addSource(groupsWithTasksCollection) {
            allTasks.postValue(it)
        }
    }

    private fun flattenTaskGroupsToList(taskGroups: List<SingleTaskGroup>): List<TaskListItem> {
        val taskListItems = mutableListOf<TaskListItem>()
        taskGroups.sortedByDescending { it.id }.forEach { taskGroup ->
            taskListItems.add(
                taskGroup.toTaskListHeader()
            )
            taskListItems.addAll(taskGroup.tasks?.sortedBy { it.id }?.map { taskEntry ->
                taskEntry.toTaskListSubItem()
            } ?: emptyList())
        }
        return taskListItems
    }

    private fun onGetAllGroupsWithSingleTasksFailure(failure: Failure) {

    }

    fun onExpanderClicked(headerItem: TaskListItem.TaskListHeader) {
        _listedTasks.postValue(_listedTasks.value?.onEach {
            if (it is TaskListItem.TaskListSubItem && it.groupId == headerItem.groupId) {
                it.visible = headerItem.expanded
            }
        })
    }

}