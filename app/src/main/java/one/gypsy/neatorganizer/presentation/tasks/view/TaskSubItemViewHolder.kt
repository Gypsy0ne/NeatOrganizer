package one.gypsy.neatorganizer.presentation.tasks.view

import one.gypsy.neatorganizer.databinding.ItemTaskBinding
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskSubItemViewHolder(val itemBinding: ItemTaskBinding, val clickListener: ClickListener) :
    TaskViewHolder(itemBinding.root) {


    interface ClickListener {
        fun onViewClick(headerItem: TaskListItem.TaskListSubItem)
    }

    override fun bind(data: TaskListItem) {
        with(data as TaskListItem.TaskListSubItem) {
            itemBinding.apply {
                this.done = this@with.done
                this.name = this@with.name
            }
        }
    }

}