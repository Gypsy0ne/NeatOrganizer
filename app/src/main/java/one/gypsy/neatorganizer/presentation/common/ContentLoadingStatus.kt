package one.gypsy.neatorganizer.presentation.common

import androidx.lifecycle.MutableLiveData
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.presentation.common.ContentLoadingStatus.Companion.EMPTY_RESOURCE_ID

sealed class ContentLoadingStatus {
    object ContentLoaded : ContentLoadingStatus()
    object ContentEmpty : ContentLoadingStatus()
    object ContentLoading : ContentLoadingStatus()

    companion object {
        const val EMPTY_RESOURCE_ID = 0
    }
}

fun ContentLoadingStatus.toRoutinesStatusAnimationResource() = when (this) {
    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_routines
    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
}

fun ContentLoadingStatus.toTasksStatusAnimationResource() = when (this) {
    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_tasks
    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
}

fun ContentLoadingStatus.toNotesStatusAnimationResource() = when (this) {
    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_notes
    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
}

fun MutableLiveData<ContentLoadingStatus>.updateLoadingStatus(items: List<*>) =
    if (items.isEmpty()) {
        postValue(ContentLoadingStatus.ContentEmpty)
    } else {
        postValue(ContentLoadingStatus.ContentLoaded)
    }
