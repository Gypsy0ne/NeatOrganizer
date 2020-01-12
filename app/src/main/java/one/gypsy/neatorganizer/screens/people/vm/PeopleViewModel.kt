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
class PeopleViewModel @Inject constructor(var getAllPeopleUseCase: GetAllPeople) : ViewModel() {

    var people: LiveData<List<Person>> = MutableLiveData<List<Person>>().default(emptyList())

    init {
        GlobalScope.launch {  people = getAllPeopleUseCase.invoke() }
    }


}