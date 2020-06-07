package one.gypsy.neatorganizer.utils.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import java.util.*


fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun Fragment.showToast(context: Context, stringId: Int) {
    Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show()
}

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