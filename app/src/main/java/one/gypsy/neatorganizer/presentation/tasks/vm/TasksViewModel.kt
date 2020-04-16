package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.GetAllGroupsWithSingleTasks
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class TasksViewModel @Inject constructor(var getAllGroupsWithSingleTasksUseCase: GetAllGroupsWithSingleTasks) :
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

    fun onExpanderClicked(headerItem: TaskListItem.TaskListHeader) {
        _listedTasks.postValue(_listedTasks.value?.onEach {
            if (it is TaskListItem.TaskListSubItem && it.groupId == headerItem.groupId) {
                it.visible = headerItem.expanded
            }
        })
    }

}