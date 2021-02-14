package one.gypsy.neatorganizer.core.binding

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

@BindingAdapter("lottieAnimationResource")
fun bindLottieAnimationResource(view: LottieAnimationView, animationResource: Int) = with(view) {
    if (animationResource != 0) {
        playAnimation(animationResource)
    } else {
        fadeOut()
    }
}

private fun LottieAnimationView.playAnimation(lottieFileId: Int) {
    setAnimation(lottieFileId)
    repeatCount = LottieDrawable.INFINITE
    repeatMode = LottieDrawable.RESTART
    fadeIn()
    playAnimation()
}
