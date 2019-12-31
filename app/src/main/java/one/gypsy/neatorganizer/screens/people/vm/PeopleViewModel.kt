package one.gypsy.neatorganizer.screens.people.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.model.Person
import java.util.*

class PeopleViewModel : ViewModel() {
    val people = MutableLiveData<List<Person>>().apply {
        value = listOf(Person("name surname", 0, Date()))
    }
}