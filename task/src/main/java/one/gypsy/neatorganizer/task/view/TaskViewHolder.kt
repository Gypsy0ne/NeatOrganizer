package one.gypsy.neatorganizer.task.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.task.model.TaskListItem

internal abstract class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: TaskListItem)
}
