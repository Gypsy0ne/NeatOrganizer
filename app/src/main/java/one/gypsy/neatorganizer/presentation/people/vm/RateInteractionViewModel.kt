package one.gypsy.neatorganizer.presentation.people.vm

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import one.gypsy.neatorganizer.presentation.profile.vm.PersonProfileViewModel
import javax.inject.Inject

class RateInteractionViewModel @AssistedInject constructor(@Assisted val personId: Long): ViewModel() {



    @AssistedInject.Factory
    interface Factory {
        fun create(personId: Long): RateInteractionViewModel
    }
}