package one.gypsy.neatorganizer.utils.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import java.util.*

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Date?.orNow() = this ?: Date()

fun View.getDimen(id: Int) = context.resources.getDimension(id).toInt()

fun EditText.requestEdit() {
    getSystemService(this.context, InputMethodManager::class.java)?.toggleSoftInput(
        InputMethodManager.HIDE_IMPLICIT_ONLY,
        0
    )
    this.requestFocus()
    setSelection(this.text.length)
}