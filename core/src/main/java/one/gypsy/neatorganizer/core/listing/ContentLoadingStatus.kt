package one.gypsy.neatorganizer.core.listing

import androidx.lifecycle.MutableLiveData
import one.gypsy.neatorganizer.core.R
import one.gypsy.neatorganizer.core.listing.ContentLoadingStatus.Companion.EMPTY_RESOURCE_ID

sealed class ContentLoadingStatus {
    object ContentLoaded : ContentLoadingStatus()
    object ContentEmpty : ContentLoadingStatus()
    object ContentLoading : ContentLoadingStatus()

    companion object {
        const val EMPTY_RESOURCE_ID = 0
    }
}

fun ContentLoadingStatus.toStatusAnimationResource(animatedImageId: Int) = when (this) {
    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
    ContentLoadingStatus.ContentEmpty -> animatedImageId
    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
}

//
// fun ContentLoadingStatus.toRoutinesStatusAnimationResource() = when (this) {
//    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
//    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_routines
//    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
// }
//
// fun ContentLoadingStatus.toTasksStatusAnimationResource(animatedImageId: Int) = when (this) {
//    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
//    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_tasks
//    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
// }
//
// fun ContentLoadingStatus.toNotesStatusAnimationResource() = when (this) {
//    ContentLoadingStatus.ContentLoaded -> EMPTY_RESOURCE_ID
//    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_notes
//    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
// }

fun MutableLiveData<ContentLoadingStatus>.updateLoadingStatus(items: List<*>) =
    if (items.isEmpty()) {
        postValue(ContentLoadingStatus.ContentEmpty)
    } else {
        postValue(ContentLoadingStatus.ContentLoaded)
    }
