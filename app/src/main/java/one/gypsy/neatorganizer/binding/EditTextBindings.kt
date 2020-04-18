package one.gypsy.neatorganizer.binding

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