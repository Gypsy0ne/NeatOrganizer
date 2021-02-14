package one.gypsy.neatorganizer.core.utils.extensions

import android.animation.Animator
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import one.gypsy.neatorganizer.R

fun View.shrink() {
    this.visibility = GONE
}

fun View.hide() {
    this.visibility = INVISIBLE
}

fun View.show() {
    this.visibility = VISIBLE
}

fun View.fadeIn() {
    alpha = 0.1f
    animate().apply {
        alpha(1f)
        duration = resources.getInteger(R.integer.minimal_animation_duration).toLong()
        setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = show()

            override fun onAnimationEnd(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }.start()
}

fun View.fadeOut() {
    alpha = 1f
    animate().apply {
        alpha(0.1f)
        duration = resources.getInteger(R.integer.minimal_animation_duration).toLong()
        setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) = shrink()

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }.start()
}

fun View.getDimen(id: Int) = context.resources.getDimension(id).toInt()

fun EditText.requestEdit() {
    ContextCompat.getSystemService(this.context, InputMethodManager::class.java)?.toggleSoftInput(
        InputMethodManager.HIDE_IMPLICIT_ONLY,
        0
    )
    this.requestFocus()
    setSelection(this.text.length)
}
