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
    private val _listedRoutines = MediatorLiveData<List<RoutineListItem>>()
    val listedRoutines: LiveData<List<RoutineListItem>> =
        Transformations.map(_listedRoutines) { tasks ->
            //tutaj wstawic filtrowanie od expanded
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
        with(_listedRoutines) {
            addSource(routines) { routines ->
                //miejsce na wrzucenie filtrowania po expand
                //pobierz aktualna widocznosc listy
                //przefiltruj dane na podstawie lsity widocznosci
                //opublikuj nowa liste z poprzednim stanem widocznosci
                this.postValue(
                    routineListMapper.mapRoutinesToVisibleListItems(
                        routines,
                        this.value ?: emptyList()
                    )
                )
            }
        }

    }

    private fun onGetAllRoutineFailure(failure: Failure) {

    }

    fun onExpand(headerItem: RoutineListItem.RoutineListHeader) {
        _listedRoutines.postValue(_listedRoutines.value?.map {
            negateExpandedIfHeader(it, headerItem)
        })
    }

    private fun negateExpandedIfHeader(
        it: RoutineListItem,
        headerItem: RoutineListItem.RoutineListHeader
    ) = if (it is RoutineListItem.RoutineListHeader && it.id == headerItem.id) {
        it.copy(expanded = !it.expanded)
    } else {
        it
    }

}