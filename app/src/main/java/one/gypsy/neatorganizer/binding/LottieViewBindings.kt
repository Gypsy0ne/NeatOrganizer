package one.gypsy.neatorganizer.binding

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.show

@BindingAdapter("lottieAnimationResource")
fun bindLottieAnimationResource(view: LottieAnimationView, animationResource: Int) = with(view) {
    if (animationResource != 0) {
        playAnimation(animationResource)
    } else {
        hide()
    }
}

private fun LottieAnimationView.playAnimation(lottieFileId: Int) {
    setAnimation(lottieFileId)
    repeatCount = LottieDrawable.INFINITE
    repeatMode = LottieDrawable.RESTART
    show()
    playAnimation()
}
