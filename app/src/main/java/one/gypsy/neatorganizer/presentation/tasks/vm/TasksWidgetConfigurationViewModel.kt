package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.CreateTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskGroupEntryItem

class TasksWidgetConfigurationViewModel(
    getAllTaskGroupEntriesUseCase: GetAllSingleTaskGroupEntries,
    private val widgetCreationUseCase: CreateTaskWidget
) :
    ViewModel() {
    private val _taskGroupEntries = MediatorLiveData<List<TaskGroupEntryItem>>()
    val taskGroupEntries: LiveData<List<TaskGroupEntryItem>>
        get() = _taskGroupEntries

    private val _selectedTaskGroup = MutableLiveData<TaskGroupEntryItem>()
    val selectedTaskGroup: LiveData<TaskGroupEntryItem>
        get() = _selectedTaskGroup

    private val _widgetCreationStatus = MutableLiveData<TaskWidgetCreationStatus>()
    val widgetCreationStatus: LiveData<TaskWidgetCreationStatus> = _widgetCreationStatus

    private var pickedColor: Int? = null

    init {
        getAllTaskGroupEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either({}, ::onGetAllSingleTaskGroupEntriesSuccess)
        }
    }

    private fun onGetAllSingleTaskGroupEntriesSuccess(taskGroupEntries: LiveData<List<SingleTaskGroupEntry>>) {
        _taskGroupEntries.addSource(taskGroupEntries) { taskGroupEntries ->
            _taskGroupEntries.postValue(taskGroupEntries.map { it.toTaskGroupEntryItem() })
        }
    }

    fun onTaskGroupSelected(selectedItem: TaskGroupEntryItem) {
        _selectedTaskGroup.postValue(selectedItem)
    }

    fun onColorPicked(color: Int) {
        pickedColor = color
    }

    fun onSubmitClicked(widgetId: Int) {
        when {
            selectedTaskGroup.value == null -> _widgetCreationStatus.postValue(
                TaskWidgetCreationStatus.TaskNotSelectedStatus
            )
            pickedColor == null -> _widgetCreationStatus.postValue(TaskWidgetCreationStatus.ColorNotPickedStatus)
            else -> submitWidgetCreation(widgetId)
        }
    }

    private fun submitWidgetCreation(widgetId: Int) {
        val taskGroup = selectedTaskGroup.value
        val widgetColor = pickedColor
        if (taskGroup != null && widgetColor != null) {
            widgetCreationUseCase.invoke(
                viewModelScope,
                CreateTaskWidget.Params(
                    TaskWidgetEntry(
                        appWidgetId = widgetId,
                        taskGroupId = taskGroup.id,
                        widgetColor = widgetColor
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

sealed class TaskWidgetCreationStatus {
    object TaskNotSelectedStatus : TaskWidgetCreationStatus()
    object ColorNotPickedStatus : TaskWidgetCreationStatus()
    object CreationSuccessStatus : TaskWidgetCreationStatus()
}