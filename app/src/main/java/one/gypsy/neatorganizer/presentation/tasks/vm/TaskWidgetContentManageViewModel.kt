package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupIdObservable
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTask
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskListSubItem

class TaskWidgetContentManageViewModel(
    private val taskGroupId: Long,
    getAllSingleTasksUseCase: GetAllSingleTasksByGroupIdObservable,
    private val updateSingleTaskUseCase: UpdateSingleTask,
    private val removeSingleTaskUseCase: RemoveSingleTask
) : ViewModel() {

    private val _listedTasks = MediatorLiveData<List<SingleTaskEntry>>()
    val listedTasks: LiveData<List<TaskListItem.TaskListSubItem>> = _listedTasks.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(it.map { it.toTaskListSubItem() })
        }
    }

    init {
        getAllSingleTasksUseCase.invoke(
            viewModelScope,
            GetAllSingleTasksByGroupIdObservable.Params(taskGroupId)
        ) {
            it.either(
                {},
                ::onGetAllSingleTasksSuccess
            )
        }
    }

    private fun onGetAllSingleTasksSuccess(tasks: LiveData<List<SingleTaskEntry>>) {
        _listedTasks.addSource(tasks) {
            _listedTasks.postValue(tasks.value)
        }
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