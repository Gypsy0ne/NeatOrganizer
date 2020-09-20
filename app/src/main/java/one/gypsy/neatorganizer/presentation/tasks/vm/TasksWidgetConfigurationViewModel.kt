package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskGroupEntryItem
import one.gypsy.neatorganizer.utils.Failure

class TasksWidgetConfigurationViewModel(getAllTaskGroupEntriesUseCase: GetAllSingleTaskGroupEntries) :
    ViewModel() {
    private val _taskGroupEntries = MediatorLiveData<List<TaskGroupEntryItem>>()
    val taskGroupEntries: LiveData<List<TaskGroupEntryItem>>
        get() = _taskGroupEntries

    init {
        getAllTaskGroupEntriesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllSingleTaskGroupEntriesFailure,
                ::onGetAllSingleTaskGroupEntriesSuccess
            )
        }
    }

    private fun onGetAllSingleTaskGroupEntriesSuccess(taskGroupEntries: LiveData<List<SingleTaskGroupEntry>>) {
        _taskGroupEntries.addSource(taskGroupEntries) { taskGroupEntries ->
            _taskGroupEntries.postValue(taskGroupEntries.map { it.toTaskGroupEntryItem() })
        }
    }

    private fun onGetAllSingleTaskGroupEntriesFailure(failure: Failure) {}


}