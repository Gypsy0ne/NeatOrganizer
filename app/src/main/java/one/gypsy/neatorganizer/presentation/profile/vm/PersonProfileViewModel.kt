package one.gypsy.neatorganizer.presentation.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import one.gypsy.neatorganizer.domain.dto.people.PersonProfile
import one.gypsy.neatorganizer.domain.interactors.profile.GetPersonProfile
import one.gypsy.neatorganizer.utils.Failure

class PersonProfileViewModel(
    getPersonProfileUseCase: GetPersonProfile,
    personId: Long
) : ViewModel() {

    private val _profile = MediatorLiveData<PersonProfile>()
    val profile: LiveData<PersonProfile>
        get() = _profile

    init {
        getPersonProfileUseCase.invoke(viewModelScope, GetPersonProfile.Params(personId)) {
            it.either(::onGetPersonProfileFailure, ::onGetPersonProfileSuccess)
        }
    }

    private fun onGetPersonProfileSuccess(personProfileSource: LiveData<PersonProfile>) {
        _profile.addSource(personProfileSource) {
            _profile.postValue(it)
        }
    }

    private fun onGetPersonProfileFailure(failure: Failure) {

    }
}