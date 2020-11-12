package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup

class TasksWidgetViewModel(
    taskGroupId: Long,
    private val widgetId: Int,
    private val getSingleTaskGroupUseCase: GetSingleTaskGroupById,
    private val updateTaskGroupUseCase: UpdateSingleTaskGroup,
) : ViewModel() {

    private val _taskGroup: MediatorLiveData<SingleTaskGroup> = MediatorLiveData()
    val taskGroup: LiveData<SingleTaskGroup> = _taskGroup
    private val _titleEdited = MutableLiveData(false)
    val titleEdited: LiveData<Boolean>
        get() = _titleEdited
    private val _widgetDataLoaded = MutableLiveData(true)
    val widgetDataLoaded: LiveData<Boolean> = _widgetDataLoaded

    init {
        loadTaskGroupData(taskGroupId)
    }

    private fun onGetSingleTaskGroupSuccess(taskGroup: LiveData<SingleTaskGroup>) {
        _taskGroup.addSource(taskGroup) {
            _taskGroup.postValue(taskGroup.value)
        }
        _widgetDataLoaded.postValue(true)
    }

    //TODO introduce 2 way data binding
    fun onTitleEditionFinished(editedTitle: String) {
        taskGroup.value?.id?.let { groupId ->
            updateTaskGroupUseCase.invoke(
                viewModelScope,
                UpdateSingleTaskGroup.Params(SingleTaskGroup(editedTitle, groupId))
            ) {
                it.either({}, {
                    _titleEdited.postValue(false)
                })
            }
        }
    }

    fun onTitleEditionStarted() {
        _titleEdited.value = true
    }

    fun loadTaskGroupData(taskGroupId: Long) = getSingleTaskGroupUseCase.invoke(
        viewModelScope,
        GetSingleTaskGroupById.Params(taskGroupId)
    ) {
        it.either(
            { _widgetDataLoaded.postValue(false) },
            ::onGetSingleTaskGroupSuccess
        )
    }

}