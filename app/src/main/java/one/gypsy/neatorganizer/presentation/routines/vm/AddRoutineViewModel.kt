package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.interactors.routines.AddRoutine
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class AddRoutineViewModel @Inject constructor(val addRoutineUseCase: AddRoutine) : ViewModel() {

    val routineTitle = MutableLiveData<String>()

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    private val _finishedAdding = MutableLiveData<Boolean>()
    val finishedAdding: LiveData<Boolean>
        get() = _finishedAdding

    fun addRoutine() {
        addRoutineUseCase.invoke(
            viewModelScope,
            AddRoutine.Params(
                Routine(
                    name = routineTitle.value.orEmpty()
                )
            )
        ) {
            it.either(::onAddSingleTaskGroupFailure, ::onAddSingleTaskGroupSuccess)
        }
    }

    fun onAddSingleTaskGroupSuccess(newTaskGroupId: Long) {
        _finishedAdding.postValue(true)
    }

    fun onAddSingleTaskGroupFailure(failure: Failure) {

    }

}