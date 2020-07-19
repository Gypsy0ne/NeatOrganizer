package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry
import one.gypsy.neatorganizer.domain.interactors.people.AddInteractionEntry
import one.gypsy.neatorganizer.utils.Failure
import one.gypsy.neatorganizer.utils.UIState
import one.gypsy.neatorganizer.utils.extensions.default
import java.util.*

class RateInteractionViewModel(
    private val addInteractionEntryUseCase: AddInteractionEntry,
    private val personId: Long
) : ViewModel() {

    private val _rating = MutableLiveData<Int>().default(3)
    val rating: LiveData<Int>
        get() = _rating

    private val _interactionUpdateStatus = MutableLiveData<UIState>()
    val interactionUpdateStatus: LiveData<UIState>
        get() = _interactionUpdateStatus

    fun onRatingChanged(level: Int, reselected: Boolean) {
        _rating.postValue(level)

    }

    private fun onAddInteractionFailure(failure: Failure) {
        //TODO handle failure
    }

    private fun onAddInteractionSuccess(unit: Unit) {
        _interactionUpdateStatus.postValue(UIState.Success)
    }

    // default rating is 3 because there is an issue with setting default value on view creation
    fun submitInteractionEntry() {
        addInteractionEntryUseCase.invoke(
            viewModelScope, AddInteractionEntry.Params(
                InteractionEntry(
                    profileId = personId,
                    interactionDate = Date(),
                    rating = _rating.value ?: 3
                )
            )
        ) {
            it.either(::onAddInteractionFailure, ::onAddInteractionSuccess)
        }
    }
}