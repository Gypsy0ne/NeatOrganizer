package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.Person

class PersonViewModel: ViewModel() {

    val person = MutableLiveData<Person>()

    fun bind(personItem: Person) {
        person.value = personItem
    }
}