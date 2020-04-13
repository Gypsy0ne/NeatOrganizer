package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

abstract class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: TaskListItem)
}
