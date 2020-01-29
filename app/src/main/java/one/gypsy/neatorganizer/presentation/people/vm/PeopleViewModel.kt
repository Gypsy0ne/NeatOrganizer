package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.*
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.interactors.GetAllPeople
import one.gypsy.neatorganizer.utils.Failure
import javax.inject.Inject
//TODO https://stackoverflow.com/questions/44270577/android-lifecycle-library-viewmodel-using-dagger-2
//TODO Change the way of exposing LiveData https://gist.github.com/humblehacker/0eb6458b1df6cf3049e031f36f0615f5
class PeopleViewModel @Inject constructor(var getAllPeopleUseCase: GetAllPeople) : ViewModel() {

    val people: LiveData<List<Person>> = MediatorLiveData()

    init {
        getAllPeopleUseCase.invoke(viewModelScope, Unit) {
            it.either(::onGetAllPeopleFailure, :: onGetAllPeopleSuccess)
        }
    }

    private fun onGetAllPeopleFailure(failure: Failure) {
        //TODO handle failure
    }

    private fun onGetAllPeopleSuccess(peopleCollectionSource: LiveData<List<Person>>) {
        (people as MediatorLiveData).addSource(peopleCollectionSource) {
            people.postValue(it)
        }
    }
}
