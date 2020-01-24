package one.gypsy.neatorganizer.screens.people.vm

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.interactors.GetPersonHistory

class PersonHistoryViewModel @AssistedInject constructor(val getPersonHistoryUseCase: GetPersonHistory, @Assisted val personId: Long): ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(personId: Long): PersonHistoryViewModel
    }

}