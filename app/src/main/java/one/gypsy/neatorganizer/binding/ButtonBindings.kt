package one.gypsy.neatorganizer.binding

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R

@BindingAdapter(value = ["expanded", "animateChanges"], requireAll = true)
fun setExpandedState(view: ImageButton, expanded: Boolean, animate: Boolean) {
    view.setImageResource(
        if (expanded) {
            R.drawable.avd_down_to_up_arrow
        } else {
            R.drawable.avd_up_to_down_arrow
        }
    )
//    if (animate) {
//        (view.drawable as AnimatedVectorDrawable).start()
//    }
}
