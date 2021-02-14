package one.gypsy.neatorganizer.task.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.core.listing.AnimatableViewHolder
import one.gypsy.neatorganizer.task.model.TaskListItem

abstract class TaskViewHolder(override val itemView: View) :
    RecyclerView.ViewHolder(itemView),
    AnimatableViewHolder {
    abstract fun bind(data: TaskListItem)
}
