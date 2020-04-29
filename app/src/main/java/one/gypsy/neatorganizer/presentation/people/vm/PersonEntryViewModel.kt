package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.dto.people.PersonEntry

class PersonEntryViewModel: ViewModel() {
    private val _person = MutableLiveData<PersonEntry>()
    val person: LiveData<PersonEntry>
        get() = _person


    fun bind(personEntryItem: PersonEntry) {
        _person.value = personEntryItem
    }


}
