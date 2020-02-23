package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AddTaskGroupViewModel @Inject constructor(): ViewModel() {

    val taskGroupTitle = MutableLiveData<String>()

}