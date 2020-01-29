package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.Person
//TODO Change the way of exposing LiveData https://gist.github.com/humblehacker/0eb6458b1df6cf3049e031f36f0615f5
class PersonViewModel: ViewModel() {

    private val _person = MutableLiveData<Person>()

    val person: LiveData<Person>
        get() = _person
        

    fun bind(personItem: Person) {
        _person.value = personItem
    }
}
