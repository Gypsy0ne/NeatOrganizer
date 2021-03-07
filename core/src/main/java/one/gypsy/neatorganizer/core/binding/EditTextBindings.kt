package one.gypsy.neatorganizer.core.binding

import android.graphics.Paint
import android.view.ActionMode
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.core.utils.extensions.requestEdit

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
        isClickable = editionEnabled
        isCursorVisible = editionEnabled
        showSoftInputOnFocus = editionEnabled
        if (editionEnabled) setEditableActionMode() else setUneditableActionMode()
        if (editionEnabled && requestEdit) {
            requestEdit()
        } else {
            clearFocus()
            closeKeyboard()
        }
    }
}

private fun EditText.setUneditableActionMode() = apply {
    customSelectionActionModeCallback = object : ActionMode.Callback2() {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            menu?.apply {
                removeItem(android.R.id.paste)
                removeItem(android.R.id.pasteAsPlainText)
                removeItem(android.R.id.replaceText)
                removeItem(android.R.id.cut)
                removeItem(android.R.id.undo)
                removeItem(android.R.id.redo)
            }
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = true
        override fun onActionItemClicked(mode: ActionMode?, item: android.view.MenuItem?) = false
        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
}

private fun EditText.setEditableActionMode() = apply {
    customSelectionActionModeCallback = object : ActionMode.Callback2() {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?) = true
        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = true
        override fun onActionItemClicked(mode: ActionMode?, item: android.view.MenuItem?) = false
        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
}

private fun View.closeKeyboard() =
    getSystemService(context, InputMethodManager::class.java)?.hideSoftInputFromWindow(
        windowToken,
        0
    )
