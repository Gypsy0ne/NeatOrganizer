package one.gypsy.neatorganizer.presentation.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.domain.dto.PersonEntry
import one.gypsy.neatorganizer.domain.dto.PersonProfile
import one.gypsy.neatorganizer.domain.interactors.GetPersonHistory
import one.gypsy.neatorganizer.domain.interactors.GetPersonProfile
import one.gypsy.neatorganizer.utils.Failure

class PersonProfileViewModel @AssistedInject constructor(val getPersonProfile: GetPersonProfile, @Assisted val personId: Long): ViewModel() {

    private val _profile = MediatorLiveData<PersonProfile>()
    val profile: LiveData<PersonProfile>
        get() = _profile

    init {
        getPersonProfile.invoke(viewModelScope, GetPersonProfile.Params(personId)) {
            it.either(::onGetPersonProfileFailure, :: onGetPersonProfileSuccess)
        }
    }

    private fun onGetPersonProfileSuccess(personProfileSource: LiveData<PersonProfile>) {
        _profile.addSource(personProfileSource) {
            _profile.postValue(it)
        }
    }

    private fun onGetPersonProfileFailure(failure: Failure) {

    }

    @AssistedInject.Factory
    interface Factory {
        fun create(personId: Long): PersonProfileViewModel
    }

}