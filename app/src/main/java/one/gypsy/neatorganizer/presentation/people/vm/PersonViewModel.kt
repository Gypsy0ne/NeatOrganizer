package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.dto.Person
class PersonViewModel: ViewModel() {

    private val _person = MutableLiveData<Person>()

    val person: LiveData<Person>
        get() = _person
        

    fun bind(personItem: Person) {
        _person.value = personItem
    }
}
