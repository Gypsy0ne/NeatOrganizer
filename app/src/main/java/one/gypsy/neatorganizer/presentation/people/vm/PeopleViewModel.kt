package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.interactors.AddInteractionEntry
import one.gypsy.neatorganizer.domain.interactors.GetAllPeople
import one.gypsy.neatorganizer.utils.CollectionUIState
import one.gypsy.neatorganizer.utils.Failure
import java.util.*
import javax.inject.Inject
//TODO https://stackoverflow.com/questions/44270577/android-lifecycle-library-viewmodel-using-dagger-2
//TODO check if interaction status updates when interaction entry is added
class PeopleViewModel @Inject constructor(var getAllPeopleUseCase: GetAllPeople) : ViewModel() {

    private val _people = MediatorLiveData<List<PersonEntry>>()
    val people: LiveData<List<PersonEntry>>
        get() = _people

    init {
        getAllPeopleUseCase.invoke(viewModelScope, Unit) {
            it.either(::onGetAllPeopleFailure, :: onGetAllPeopleSuccess)
        }
    }

    private fun onGetAllPeopleFailure(failure: Failure) {
        //TODO handle failure
    }

    private fun onGetAllPeopleSuccess(peopleCollectionSource: LiveData<List<PersonEntry>>) {
        _people.addSource(peopleCollectionSource) {
            _people.postValue(it)
        }
    }




}
