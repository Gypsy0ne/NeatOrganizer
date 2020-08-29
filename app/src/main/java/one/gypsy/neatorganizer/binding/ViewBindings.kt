package one.gypsy.neatorganizer.binding

import android.view.View
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R

//TODO extract numbers to integers resources
@BindingAdapter("lastInteractionBackground")
fun setInteractionBasedBackground(view: View, lastInteractionInDays: Int) {
    val backgroundResource = when (lastInteractionInDays) {
        in 0..7 -> R.drawable.item_person_green_warning_background
        in 8..14 -> R.drawable.item_person_yellow_warning_background
        else -> R.drawable.item_person_red_warning_background
    }
    view.background = view.context.getDrawable(backgroundResource)
}
