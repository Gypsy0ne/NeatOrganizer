package one.gypsy.neatorganizer.presentation.common

import androidx.lifecycle.LiveData

interface RemoveViewModel {
    val actionFinished: LiveData<Boolean>
    fun onRemoveSubmit(removedItemId: Long)
}