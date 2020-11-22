package one.gypsy.neatorganizer.binding

import android.graphics.Paint
import android.widget.EditText
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.utils.extensions.requestEdit

@BindingAdapter("strokeThrough")
fun setStrikeThrough(view: EditText, strokeThrough: Boolean) {
    if (strokeThrough) {
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        view.paintFlags = view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("editionEnabled")
fun setEditionEnabled(view: EditText, enabled: Boolean) {
    view.apply {
        isFocusable = enabled
        isFocusableInTouchMode = enabled
        isEnabled = enabled
        isClickable = enabled

        if (enabled) {
            requestEdit()
        } else {
            clearFocus()
        }
    }
}