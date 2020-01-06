package one.gypsy.neatorganizer.screens.people.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import one.gypsy.neatorganizer.domain.Person
import one.gypsy.neatorganizer.interactors.AddPerson
import one.gypsy.neatorganizer.interactors.GetAllPeople
import one.gypsy.neatorganizer.utils.default
import java.util.*
import javax.inject.Inject
//TODO https://stackoverflow.com/questions/44270577/android-lifecycle-library-viewmodel-using-dagger-2
class PeopleViewModel @Inject constructor(var addPersonUseCase: AddPerson, var getAllPeopleUseCase: GetAllPeople) : ViewModel() {

    var people: LiveData<List<Person>> = MutableLiveData<List<Person>>().apply { value = emptyList() }

    init {
        Log.i("VM", people.value.toString())
        GlobalScope.launch {  people = getAllPeopleUseCase.invoke() }
    }

    fun addPerson(/*person: PersonPut parameters from input fields*/) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                addPersonUseCase.invoke(Person("postac testowa", 1337, Date()))
            }
        }
        //TODO load the list again if livedata room observer does not update this.people
    }

}