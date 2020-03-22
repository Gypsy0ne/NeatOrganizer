package one.gypsy.neatorganizer.presentation.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.interactors.AddInteractionEntry
import java.util.*
import javax.inject.Inject

class InteractionEntryViewModel: ViewModel() {
    private val _interactionEntry = MutableLiveData<InteractionEntry>()
    val interactionEntry: LiveData<InteractionEntry>
        get() = _interactionEntry


    fun bind(interactionEntryItem: InteractionEntry) {
        _interactionEntry.value = interactionEntryItem
    }


}
