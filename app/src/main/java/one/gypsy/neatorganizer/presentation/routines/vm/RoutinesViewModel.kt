package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.interactors.routines.GetAllRoutines
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListMapper
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject

class RoutinesViewModel @Inject constructor(
    val getAllRoutinesUseCase: GetAllRoutines,
    val routineListMapper: RoutineListMapper
) : ViewModel() {
    private val allRoutines = MediatorLiveData<List<Routine>>()
    private val _listedRoutines =
        MediatorLiveData<List<RoutineListItem>>().apply {
            addSource(allRoutines) { routines ->
                postValue(routineListMapper.mapRoutinesToRoutineListItems(routines))
            }
        }
    val listedRoutines: LiveData<List<RoutineListItem>> =
        Transformations.map(_listedRoutines) { tasks ->
            tasks
        }

    init {
        getAllRoutinesUseCase.invoke(viewModelScope, Unit) {
            it.either(
                ::onGetAllRoutineFailure,
                ::onGetAllRoutineSuccess
            )
        }
    }


    private fun onGetAllRoutineSuccess(routines: LiveData<List<Routine>>) {
        allRoutines.addSource(routines) {
            allRoutines.postValue(it)
        }
    }

    private fun onGetAllRoutineFailure(failure: Failure) {

    }
}