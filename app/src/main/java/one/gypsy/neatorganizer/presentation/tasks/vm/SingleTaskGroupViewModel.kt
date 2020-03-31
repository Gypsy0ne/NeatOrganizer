package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.utils.extensions.default

class SingleTaskGroupViewModel(singleTaskGroup: SingleTaskGroup) : ViewModel() {

    private val _tasks = MutableLiveData<List<SingleTaskEntry>>()
    val tasks: LiveData<List<SingleTaskEntry>>
        get() = _tasks

    private val _expanded = MutableLiveData<Boolean>().default(false)
    val expanded: LiveData<Boolean>
        get() = _expanded

    init {
        _tasks.value = singleTaskGroup.tasks
    }

    fun onExpandTrigger() {
        _expanded.postValue(!(_expanded.value ?: true))
    }

}