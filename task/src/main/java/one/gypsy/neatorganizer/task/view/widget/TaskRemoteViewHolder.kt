package one.gypsy.neatorganizer.task.view.widget

import android.graphics.Paint
import android.widget.RemoteViews
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.model.TaskEntryWidgetItem

internal class TaskRemoteViewHolder(
    widgetItem: TaskEntryWidgetItem,
    packageName: String,
    layoutId: Int
) : RemoteViews(packageName, layoutId) {

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
