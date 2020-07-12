package one.gypsy.neatorganizer.presentation.common

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import one.gypsy.neatorganizer.R

interface AnimatableViewHolder {
    val itemView: View

    fun clearAnimation() {
        itemView.clearAnimation()
    }

    fun animate() =
        itemView.startAnimation(
            AnimationUtils.loadAnimation(
                itemView.context,
                R.anim.item_animation_enter_from_right
            )
        )

    fun animate(onAnimationFinished: () -> Unit) {
        val animation = AnimationUtils.loadAnimation(
            itemView.context,
            R.anim.item_animation_enter_from_right
        ).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onAnimationFinished.invoke()
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
        }
        itemView.startAnimation(animation)
    }
}