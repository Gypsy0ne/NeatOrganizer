package one.gypsy.neatorganizer.task.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntryDto
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateTaskWidgetLinkedGroup
import one.gypsy.neatorganizer.task.model.WidgetTaskGroupItem
import one.gypsy.neatorganizer.task.model.toTaskGroupEntryItem

internal class TaskWidgetSelectionViewModel(
    getAllTaskGroupEntriesUseCase: GetAllSingleTaskGroupEntries,
    private val widgetUpdateUseCase: UpdateTaskWidgetLinkedGroup
) : ViewModel() {

    private val _listedTaskGroups = MediatorLiveData<List<WidgetTaskGroupItem>>()
    val listedTaskGroups: LiveData<List<WidgetTaskGroupItem>> = _listedTaskGroups

    private val _selectedTaskGroup = MutableLiveData<WidgetTaskGroupItem>()
    val selectedTaskGroup: LiveData<WidgetTaskGroupItem> = _selectedTaskGroup

    private val _widgetSelectionStatus = MutableLiveData<TaskWidgetSelectionStatus>()
    val widgetSelectionStatus: LiveData<TaskWidgetSelectionStatus> = _widgetSelectionStatus

    init {
        getAllTaskGroupEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either(onSuccess = ::onGetAllSingleTaskGroupEntriesSuccess)
        }
    }

    private fun onGetAllSingleTaskGroupEntriesSuccess(taskGroupEntriesObservable: LiveData<List<SingleTaskGroupEntryDto>>) =
        _listedTaskGroups.addSource(taskGroupEntriesObservable) { taskGroupEntries ->
            _listedTaskGroups.postValue(taskGroupEntries.map { it.toTaskGroupEntryItem() })
        }

    fun onItemSelected(selectedItem: WidgetTaskGroupItem) =
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
            ) { it.either(onSuccess = ::onUpdateTaskWidgetSuccess) }
        }

    private fun onUpdateTaskWidgetSuccess(unit: Unit) =
        _widgetSelectionStatus.postValue(TaskWidgetSelectionStatus.SelectionSuccessStatus)
}

sealed class TaskWidgetSelectionStatus {
    object TaskGroupNotSelectedStatus : TaskWidgetSelectionStatus()
    object SelectionSuccessStatus : TaskWidgetSelectionStatus()
}
