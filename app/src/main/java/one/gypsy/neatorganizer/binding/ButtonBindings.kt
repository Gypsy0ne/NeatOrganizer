package one.gypsy.neatorganizer.binding

import android.graphics.drawable.AnimatedVectorDrawable
import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R

@BindingAdapter("expanderState")
fun setExpandedState(view: ImageButton, expanded: Boolean) {
    view.setImageResource(
        if (expanded) {
            R.drawable.avd_down_to_up_arrow
        } else {
            R.drawable.avd_up_to_down_arrow
        }
    )
    (view.drawable as AnimatedVectorDrawable).start()
}
