package one.gypsy.neatorganizer.presentation.tasks.view.widget

import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.databinding.ItemTaskGroupEntryBinding
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem

class TaskGroupEntryViewHolder(private val itemBinding: ItemTaskGroupEntryBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(taskGroupEntry: TaskGroupEntryItem) {
        itemBinding.apply {
            name = taskGroupEntry.name
            tasksCount = taskGroupEntry.tasksCount
            tasksDone = taskGroupEntry.tasksDone
        }
    }
}