package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.presentation.common.AnimatableViewHolder
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

abstract class TaskViewHolder(override val itemView: View) :
    RecyclerView.ViewHolder(itemView),
    AnimatableViewHolder {
    abstract fun bind(data: TaskListItem)
}
