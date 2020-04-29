package one.gypsy.neatorganizer.presentation.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry

class InteractionEntryViewModel: ViewModel() {
    private val _interactionEntry = MutableLiveData<InteractionEntry>()
    val interactionEntry: LiveData<InteractionEntry>
        get() = _interactionEntry


    fun bind(interactionEntryItem: InteractionEntry) {
        _interactionEntry.value = interactionEntryItem
    }


}
