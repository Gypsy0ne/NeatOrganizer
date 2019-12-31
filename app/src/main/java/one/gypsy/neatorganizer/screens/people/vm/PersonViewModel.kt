package one.gypsy.neatorganizer.screens.people.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.model.Person

class PersonViewModel: ViewModel() {

    val name = MutableLiveData<String>()

    fun bind(person: Person) {
        name.value = person.name
    }
}