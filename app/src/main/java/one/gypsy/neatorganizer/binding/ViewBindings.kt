package one.gypsy.neatorganizer.binding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R

//TODO extract numbers to integers resources
@BindingAdapter("lastInteractionBackground")
fun setInteractionBasedBackground(view: View, lastInteractionInDays: Int) {
    var backgroundResource = when(lastInteractionInDays) {
        in 0..7 -> R.drawable.item_person_green_warning_background
        in 8..14 -> R.drawable.item_person_yellow_warning_background
        else -> R.drawable.item_person_red_warning_background
    }
    view.background = view.context.getDrawable(backgroundResource)
}


@BindingAdapter("expanded")
fun animateVisibilityChanges(view: View, expanded: Boolean) {
    if(expanded) {
        //TODO add open animation
        val heightChange = ObjectAnimator.ofFloat(view, "y", view.y,
            0f).apply {
            duration = 400
        }
        heightChange.addListener(onEnd = {
            view.visibility = View.GONE
        })
        AnimatorSet().play(heightChange)
    } else {
        //TODO add collapse animation
    }
}