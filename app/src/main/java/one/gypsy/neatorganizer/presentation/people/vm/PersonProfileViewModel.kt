package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.domain.interactors.GetPersonHistory

class PersonProfileViewModel @AssistedInject constructor(val getPersonHistoryUseCase: GetPersonHistory, @Assisted val personId: Long): ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(personId: Long): PersonProfileViewModel
    }

}