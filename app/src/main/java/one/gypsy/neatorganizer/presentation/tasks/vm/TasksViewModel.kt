package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.AddTask
import one.gypsy.neatorganizer.domain.interactors.GetAllGroupsWithSingleTasks
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
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
                TaskListItem.TaskListHeader(
                    taskGroup.id,
                    taskGroup.name,
                    true,
                    taskGroup.id,
                    taskGroup.tasks?.size ?: 0
                )
            )
            taskListItems.addAll(taskGroup.tasks?.sortedByDescending { it.id }?.map { taskEntry ->
                TaskListItem.TaskListSubItem(
                    taskEntry.id,
                    taskEntry.name,
                    false,
                    taskGroup.id,
                    taskEntry.done

                )
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