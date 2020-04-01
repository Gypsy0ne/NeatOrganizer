package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.GetAllGroupsWithSingleTasks
import one.gypsy.neatorganizer.presentation.tasks.view.TaskListItem
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class TasksViewModel @Inject constructor(var getAllGroupsWithSingleTasksUseCase: GetAllGroupsWithSingleTasks) :
    ViewModel() {

    //Operations on nested fields will have to registered manually inside the list and database with use cases
    private val _tasks = MediatorLiveData<List<TaskListItem>>()
    val tasks: LiveData<List<TaskListItem>>
        get() = _tasks

    init {
        getAllGroupsWithSingleTasksUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllGroupsWithSingleTasksFailure,
                ::onGetAllGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun onGetAllGroupsWithSingleTasksSuccess(groupsWithTasksCollection: LiveData<List<SingleTaskGroup>>) {
        _tasks.addSource(groupsWithTasksCollection) {
            _tasks.postValue( flattenTaskGroupsToList(
                it
            ))
        }
    }

    private fun flattenTaskGroupsToList(taskGroups: List<SingleTaskGroup>): List<TaskListItem> {
        val taskListItems = mutableListOf<TaskListItem>()
        taskGroups.forEach { taskGroup ->
            taskListItems.add(
                (TaskListItem(
                    taskGroup.id,
                    taskGroup.name,
                    false,
                    taskGroup.id,
                    true
                ))
            )
            taskListItems.addAll(taskGroup.tasks?.map { taskEntry ->
                TaskListItem(
                    taskEntry.id,
                    taskEntry.name,
                    taskEntry.done,
                    taskGroup.id,
                    false
                )
            } ?: emptyList())
        }

        return taskListItems
    }

    private fun onGetAllGroupsWithSingleTasksFailure(failure: Failure) {

    }

}