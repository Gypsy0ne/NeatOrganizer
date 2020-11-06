package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.domain.interactors.tasks.SaveTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateTaskWidget
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskGroupEntryItem

class TaskWidgetSelectionViewModel(
    getAllTaskGroupEntriesUseCase: GetAllSingleTaskGroupEntries,
    private val widgetUpdateUseCase: UpdateTaskWidget
) :
    ViewModel() {
    private val _listedTaskGroups = MediatorLiveData<List<TaskGroupEntryItem>>()
    val listedTaskGroups: LiveData<List<TaskGroupEntryItem>>
        get() = _listedTaskGroups

    private val _selectedTaskGroup = MutableLiveData<TaskGroupEntryItem>()
    val selectedTaskGroup: LiveData<TaskGroupEntryItem>
        get() = _selectedTaskGroup

    private val _widgetCreationStatus = MutableLiveData<TaskWidgetCreationStatus>()
    val widgetCreationStatus: LiveData<TaskWidgetCreationStatus> = _widgetCreationStatus


    init {
        getAllTaskGroupEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either({}, ::onGetAllSingleTaskGroupEntriesSuccess)
        }
    }

    private fun onGetAllSingleTaskGroupEntriesSuccess(taskGroupEntries: LiveData<List<SingleTaskGroupEntry>>) {
        _listedTaskGroups.addSource(taskGroupEntries) { taskGroupEntries ->
            _listedTaskGroups.postValue(taskGroupEntries.map { it.toTaskGroupEntryItem() })
        }
    }

    fun onTaskGroupSelected(selectedItem: TaskGroupEntryItem) {
        _selectedTaskGroup.postValue(selectedItem)
    }


    fun onSubmitClicked(widgetId: Int) {
        when {
            selectedTaskGroup.value == null -> _widgetCreationStatus.postValue(
                TaskWidgetCreationStatus.TaskNotSelectedStatus
            )
            else -> submitWidgetCreation(widgetId)
        }
    }

    private fun submitWidgetCreation(widgetId: Int) {
        val taskGroup = selectedTaskGroup.value
        if (taskGroup != null) {
            widgetCreationUseCase.invoke(
                viewModelScope,
                SaveTaskWidget.Params(
                    TaskWidgetEntry(
                        appWidgetId = widgetId,
                        taskGroupId = taskGroup.id,
                        widgetColor = widgetColor,
                        taskGroupTitle = selectedTaskGroup.value?.name.orEmpty()
                    )
                )
            ) {
                it.either({}, ::onCreateTaskWidgetSuccess)
            }
        }
    }

    private fun onCreateTaskWidgetSuccess(unit: Unit) {
        _widgetCreationStatus.postValue(TaskWidgetCreationStatus.CreationSuccessStatus)
    }
}

sealed class TaskWidgetSelectionStatus {
    object TaskGroupNotSelectedStatus : TaskWidgetSelectionStatus()
    object SelectionSuccessStatus : TaskWidgetSelectionStatus()
}