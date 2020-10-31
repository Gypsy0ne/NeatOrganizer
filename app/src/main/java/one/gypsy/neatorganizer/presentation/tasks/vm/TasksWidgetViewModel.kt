package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.model.toSingleTask

class TasksWidgetViewModel(
    private val taskGroupId: Long,
    private val widgetId: Int,
    getSingleTaskGroupUseCase: GetSingleTaskGroupById,
    private val updateTaskGroupUseCase: UpdateSingleTaskGroup,
    private val updateSingleTaskUseCase: UpdateSingleTask,
    private val removeSingleTaskUseCase: RemoveSingleTask
) : ViewModel() {

    private val _taskGroupTitle: MediatorLiveData<String> = MediatorLiveData()
    val taskGroupTitle: LiveData<String> = _taskGroupTitle
    private val _titleEdited = MutableLiveData(false)
    val titleEdited: LiveData<Boolean>
        get() = _titleEdited

    init {
        getSingleTaskGroupUseCase.invoke(
            viewModelScope,
            GetSingleTaskGroupById.Params(taskGroupId)
        ) {
            it.either(
                {},
                ::onGetSingleTaskGroupSuccess
            )
        }
    }

    private fun onGetSingleTaskGroupSuccess(taskGroup: LiveData<SingleTaskGroup>) {
        _taskGroupTitle.addSource(taskGroup) {
            _taskGroupTitle.postValue(taskGroup.value?.name)
        }
    }

    fun onTitleEditionFinished(editedTitle: String) {
        updateTaskGroupUseCase.invoke(
            viewModelScope,
            UpdateSingleTaskGroup.Params(SingleTaskGroup(editedTitle, taskGroupId))
        ) {
            it.either({}, {
                _titleEdited.postValue(false)
            })
        }
    }

    fun onTitleEditionStarted() {
        _titleEdited.value = true
    }


    fun onTaskUpdate(subItem: TaskListItem.TaskListSubItem) {
        updateSingleTaskUseCase.invoke(
            viewModelScope,
            UpdateSingleTask.Params(singleTask = subItem.toSingleTask())
        )
    }

    fun onRemove(subItem: TaskListItem.TaskListSubItem) {
        removeSingleTaskUseCase.invoke(
            viewModelScope,
            RemoveSingleTask.Params(subItem.toSingleTask())
        )
    }

}