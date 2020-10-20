package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupById

class TasksWidgetViewModel(
    private val taskGroupId: Long,
    private val widgetId: Int,
    getSingleTaskGroupUseCase: GetSingleTaskGroupById
) : ViewModel() {

    private val _taskGroupTitle: MediatorLiveData<String> = MediatorLiveData()
    val taskGroupTitle: LiveData<String> = _taskGroupTitle
    private val _titleEdited = MutableLiveData(false)
    val titleEdited: LiveData<Boolean> = _titleEdited

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
}