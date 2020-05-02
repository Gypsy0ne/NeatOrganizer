package one.gypsy.neatorganizer.presentation.routines.vm

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class AddRoutineTaskViewModel @AssistedInject constructor(
    @Assisted val routineId: Long
) : ViewModel() {


    @AssistedInject.Factory
    interface Factory {
        fun create(routineId: Long): AddRoutineTaskViewModel
    }
}