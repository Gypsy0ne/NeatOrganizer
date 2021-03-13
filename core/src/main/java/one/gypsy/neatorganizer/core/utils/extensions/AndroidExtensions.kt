package one.gypsy.neatorganizer.core.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

fun Context.showShortToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
