package one.gypsy.neatorganizer.binding

import android.graphics.Paint
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("editable")
fun setEditable(textField: EditText, editable: Boolean) {
    textField.apply {
        isFocusable = editable
        isClickable = editable
        isFocusableInTouchMode = editable
        isEnabled = editable
    }
}

@BindingAdapter("strokeThrough")
fun setStrikeThrough(view: EditText, strokeThrough: Boolean) {
    if(strokeThrough) {
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        view.paintFlags = view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}