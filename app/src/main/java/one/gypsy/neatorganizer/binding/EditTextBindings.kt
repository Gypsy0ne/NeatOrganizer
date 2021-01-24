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

@BindingAdapter(value = ["editionEnabled", "requestEdit"], requireAll = false)
fun setEditionEnabled(view: EditText, editionEnabled: Boolean, requestEdit: Boolean = false) {
    view.apply {
        isFocusable = editionEnabled
        isFocusableInTouchMode = editionEnabled
        isEnabled = editionEnabled
        isClickable = editionEnabled

        if (editionEnabled && requestEdit) {
            requestEdit()
        } else {
            clearFocus()
        }
    }
}
