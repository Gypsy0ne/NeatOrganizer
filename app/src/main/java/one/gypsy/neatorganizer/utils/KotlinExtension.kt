package one.gypsy.neatorganizer.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun Fragment.showToast(context: Context, stringId: Int) {
    Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show()
}