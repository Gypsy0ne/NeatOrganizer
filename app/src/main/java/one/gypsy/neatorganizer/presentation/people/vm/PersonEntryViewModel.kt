package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.interactors.AddInteractionEntry
import java.util.*
import javax.inject.Inject

class PersonEntryViewModel: ViewModel() {
    private val _person = MutableLiveData<PersonEntry>()
    val person: LiveData<PersonEntry>
        get() = _person


    fun bind(personEntryItem: PersonEntry) {
        _person.value = personEntryItem
    }


}
