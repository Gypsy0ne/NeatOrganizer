package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTask
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskListSubItem

class TaskWidgetManageViewModel(
    private val taskGroupId: Long,
    getSingleTaskGroupUseCase: GetSingleTaskGroupById,
    private val updateSingleTaskUseCase: UpdateSingleTask,
    private val removeSingleTaskUseCase: RemoveSingleTask
) : ViewModel() {

    private val _taskGroup = MediatorLiveData<SingleTaskGroup>()
    val listedTasks: LiveData<List<TaskListItem.TaskListSubItem>> = _taskGroup.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(it.tasks.map { it.toTaskListSubItem() })
        }
    }
    val taskGroupTitle: LiveData<String> = _taskGroup.switchMap {
        liveData { emit(it.name) }
    }

    private val _titleEdited = MutableLiveData<Boolean>(false)
    val titleEdited: LiveData<Boolean> = _titleEdited

    init {
        getSingleTaskGroupUseCase.invoke(
            viewModelScope,
            GetSingleTaskGroupById.Params(taskGroupId)
        ) {
            it.either(
                {},
                ::onGetGroupsWithSingleTasksSuccess
            )
        }
    }

    private fun onGetGroupsWithSingleTasksSuccess(taskGroup: LiveData<SingleTaskGroup>) {
        _taskGroup.addSource(taskGroup) {
            _taskGroup.postValue(taskGroup.value)
        }
    }

    fun onClick() {

    }

    fun onTaskUpdate(taskItem: TaskListItem.TaskListSubItem) {
        updateSingleTaskUseCase.invoke(
            viewModelScope,
            UpdateSingleTask.Params(singleTask = taskItem.toSingleTask())
        )
    }

    fun onRemove(taskItem: TaskListItem.TaskListSubItem) {
        removeSingleTaskUseCase.invoke(
            viewModelScope,
            RemoveSingleTask.Params(taskItem.toSingleTask())
        )
    }
}