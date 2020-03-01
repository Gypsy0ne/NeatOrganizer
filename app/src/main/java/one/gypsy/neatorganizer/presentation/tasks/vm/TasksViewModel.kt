package one.gypsy.neatorganizer.presentation.tasks.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.GetAllGroupsWithSingleTasks
import javax.inject.Inject

class TasksViewModel @Inject constructor(var getAllGroupsWithSingleTasksUseCase: GetAllGroupsWithSingleTasks) :
    ViewModel() {

    //Operations on nested fields will have to registered manually inside the list and database with use cases
    private val _groupsWithSingleTasks = MediatorLiveData<List<SingleTaskGroup>>()
    val groupsWithSingleTasks: LiveData<List<SingleTaskGroup>>
        get() = _groupsWithSingleTasks


}