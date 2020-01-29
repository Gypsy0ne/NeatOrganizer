package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.Person
//TODO Change the way of exposing LiveData https://gist.github.com/humblehacker/0eb6458b1df6cf3049e031f36f0615f5
class PersonViewModel: ViewModel() {

    val person: LiveData<Person> = MutableLiveData<Person>()

    fun bind(personItem: Person) {
        (person as MutableLiveData).value = personItem
    }
}
