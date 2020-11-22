package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.graphics.Paint
import android.widget.RemoteViews
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.presentation.tasks.model.TaskEntryWidgetItem

class TaskRemoteViewHolder(widgetItem: TaskEntryWidgetItem, packageName: String, layoutId: Int) :
    RemoteViews(packageName, layoutId) {

    init {
        styleTaskTextView(widgetItem)
    }

    private fun styleTaskTextView(widgetItem: TaskEntryWidgetItem) {
        val paintFlags = getPaintFlags(widgetItem.done)
        setInt(R.id.taskText, "setPaintFlags", paintFlags)
        setTextViewText(R.id.taskText, widgetItem.text)
    }

    private fun getPaintFlags(done: Boolean): Int = if (done) {
        Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
    } else {
        Paint.STRIKE_THRU_TEXT_FLAG.inv() and Paint.ANTI_ALIAS_FLAG
    }
}
