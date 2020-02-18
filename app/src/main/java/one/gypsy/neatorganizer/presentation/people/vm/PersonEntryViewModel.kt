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

class PersonEntryViewModel /*@Inject constructor(
    var addInteractionEntryUseCase: AddInteractionEntry
) */: ViewModel() {
    private val _person = MutableLiveData<PersonEntry>()
    val person: LiveData<PersonEntry>
        get() = _person

    val personEntry: LiveData<PersonEntry>
        get() = _person


    fun bind(personEntryItem: PersonEntry) {
        _person.value = personEntryItem
    }
//TODO UseCase injection is needed, ViewModel is created manually in adapter so no injection is performed
//    fun onUpdateInteractionClick() {
//        val personId = person.value?.id
//        if (personId != null) {
//            addInteractionEntryUseCase.invoke(
//                viewModelScope, AddInteractionEntry.Params(
//                    InteractionEntry(personId, Date())
//                )
//            ) {
//
//            }
//        }
//    }

}
