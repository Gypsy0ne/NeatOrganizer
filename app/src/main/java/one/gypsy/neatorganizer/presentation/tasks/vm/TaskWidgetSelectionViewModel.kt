package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.domain.tasks.UpdateTaskWidgetLinkedGroup
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem

class TaskWidgetSelectionViewModel(
    getAllTaskGroupEntriesUseCase: GetAllSingleTaskGroupEntries,
    private val widgetUpdateUseCase: UpdateTaskWidgetLinkedGroup
) : ViewModel() {

    private val _listedTaskGroups = MediatorLiveData<List<TaskGroupEntryItem>>()
    val listedTaskGroups: LiveData<List<TaskGroupEntryItem>> = _listedTaskGroups

    private val _selectedTaskGroup = MutableLiveData<TaskGroupEntryItem>()
    val selectedTaskGroup: LiveData<TaskGroupEntryItem> = _selectedTaskGroup

    private val _widgetSelectionStatus = MutableLiveData<TaskWidgetSelectionStatus>()
    val widgetSelectionStatus: LiveData<TaskWidgetSelectionStatus> = _widgetSelectionStatus

    init {
        getAllTaskGroupEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either({}, ::onGetAllSingleTaskGroupEntriesSuccess)
        }
    }

    private fun onGetAllSingleTaskGroupEntriesSuccess(taskGroupEntriesObservable: LiveData<List<SingleTaskGroupEntry>>) =
        _listedTaskGroups.addSource(taskGroupEntriesObservable) { taskGroupEntries ->
            _listedTaskGroups.postValue(taskGroupEntries.map { it.toTaskGroupEntryItem() })
        }

    fun onTaskGroupSelected(selectedItem: TaskGroupEntryItem) =
        _selectedTaskGroup.postValue(selectedItem)

    fun onSubmitClicked(widgetId: Int) = if (selectedTaskGroup.value == null) {
        _widgetSelectionStatus.postValue(
            TaskWidgetSelectionStatus.TaskGroupNotSelectedStatus
        )
    } else {
        submitWidgetCreation(widgetId)
    }

    private fun submitWidgetCreation(widgetId: Int) =
        selectedTaskGroup.value?.id?.let { groupId ->
            widgetUpdateUseCase.invoke(
                viewModelScope,
                UpdateTaskWidgetLinkedGroup.Params(widgetId, groupId)
            ) { it.either({}, ::onUpdateTaskWidgetSuccess) }
        }

    private fun onUpdateTaskWidgetSuccess(unit: Unit) =
        _widgetSelectionStatus.postValue(TaskWidgetSelectionStatus.SelectionSuccessStatus)
}

sealed class TaskWidgetSelectionStatus {
    object TaskGroupNotSelectedStatus : TaskWidgetSelectionStatus()
    object SelectionSuccessStatus : TaskWidgetSelectionStatus()
}
