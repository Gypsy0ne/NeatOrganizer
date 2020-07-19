package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry
import one.gypsy.neatorganizer.domain.interactors.people.GetAllPeople
import one.gypsy.neatorganizer.utils.Failure

//TODO https://stackoverflow.com/questions/44270577/android-lifecycle-library-viewmodel-using-dagger-2
//TODO check if interaction status updates when interaction entry is added
class PeopleViewModel(getAllPeopleUseCase: GetAllPeople) : ViewModel() {

    private val _people = MediatorLiveData<List<PersonEntry>>()
    val people: LiveData<List<PersonEntry>>
        get() = _people

    init {
        getAllPeopleUseCase.invoke(viewModelScope, Unit) {
            it.either(::onGetAllPeopleFailure, ::onGetAllPeopleSuccess)
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
