package one.gypsy.neatorganizer.presentation.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.domain.dto.people.PersonProfile
import one.gypsy.neatorganizer.domain.interactors.people.GetPersonProfile
import one.gypsy.neatorganizer.utils.Failure

class PersonProfileViewModel @AssistedInject constructor(var getPersonProfileUseCase: GetPersonProfile, @Assisted val personId: Long): ViewModel() {

    private val _profile = MediatorLiveData<PersonProfile>()
    val profile: LiveData<PersonProfile>
        get() = _profile

    init {
        getPersonProfileUseCase.invoke(viewModelScope, GetPersonProfile.Params(personId)) {
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