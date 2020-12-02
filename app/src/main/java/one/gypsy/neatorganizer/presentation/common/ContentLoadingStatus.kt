package one.gypsy.neatorganizer.presentation.common

import one.gypsy.neatorganizer.R

sealed class ContentLoadingStatus {
    object ContentLoaded : ContentLoadingStatus()
    object ContentEmpty : ContentLoadingStatus()
    object ContentLoading : ContentLoadingStatus()
}

fun ContentLoadingStatus.toRoutinesStatusAnimationResource() = when (this) {
    ContentLoadingStatus.ContentLoaded -> 0
    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_routines
    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
}

fun ContentLoadingStatus.toTasksStatusAnimationResource() = when (this) {
    ContentLoadingStatus.ContentLoaded -> 0
    ContentLoadingStatus.ContentEmpty -> R.raw.lottie_empty_tasks
    ContentLoadingStatus.ContentLoading -> R.raw.lottie_loading
}
