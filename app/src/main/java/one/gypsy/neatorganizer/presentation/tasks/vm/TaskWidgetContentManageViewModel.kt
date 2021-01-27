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
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupWithTasksById
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTask
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskListSubItem

class TaskWidgetContentManageViewModel(
    taskGroupId: Long,
    private val getAllSingleTasksUseCase: GetSingleTaskGroupWithTasksById,
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

    init {
        loadTasksData(taskGroupId)
    }

    private fun onGetAllSingleTasksSuccess(taskGroup: LiveData<SingleTaskGroupWithTasks>) {
        // introduce distinction cause listed tasks will get triggered on title change
        _listedTasks.addSource(taskGroup) {
            _listedTasks.postValue(taskGroup.value?.tasks)
        }
        // this is the place where dto model should be composition   
        _taskGroup.addSource(taskGroup) {
            _listedTasks.postValue(taskGroup.value)
        }
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
                it.either(
                    {},
                    { _titleEdited.postValue(false) }
                )
            }
        }
    }

    fun onTitleEditionStarted() = _titleEdited.postValue(true)

    fun onTaskUpdate(taskItem: TaskListItem.TaskListSubItem) = updateSingleTaskUseCase.invoke(
        viewModelScope,
        UpdateSingleTask.Params(singleTask = taskItem.toSingleTask())
    )

    fun onRemove(taskItem: TaskListItem.TaskListSubItem) = removeSingleTaskUseCase.invoke(
        viewModelScope,
        RemoveSingleTask.Params(taskItem.toSingleTask())
    )

    fun loadTasksData(taskGroupId: Long) = getAllSingleTasksUseCase.invoke(
        viewModelScope,
        GetSingleTaskGroupWithTasksById.Params(taskGroupId)
    ) { it.either({}, ::onGetAllSingleTasksSuccess) }
}
