package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.AddSingleTaskGroup
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class AddSingleTaskGroupViewModel @Inject constructor(var addSingleTaskGroupUseCase: AddSingleTaskGroup): ViewModel() {

    val taskGroupTitle = MutableLiveData<String>()

    fun addTask() {
        addSingleTaskGroupUseCase.invoke(viewModelScope, AddSingleTaskGroup.Params(SingleTaskGroup(taskGroupTitle.value ?: ""))) {
            it.either(::onAddSingleTaskGroupFailure, ::onAddSingleTaskGroupSuccess)
        }
    }

    fun onAddSingleTaskGroupSuccess(newTaskGroupId: Long) {

    }

    fun onAddSingleTaskGroupFailure(failure: Failure) {

    }

}