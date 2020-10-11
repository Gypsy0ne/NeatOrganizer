package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.graphics.drawable.TransitionDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ItemTaskGroupEntryBinding
import one.gypsy.neatorganizer.presentation.common.LifecycleViewHolder
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem

class TaskGroupEntryViewHolder(
    private val currentlySelectedItem: LiveData<TaskGroupEntryItem>,
    private val onSelected: (TaskGroupEntryItem) -> Unit,
    private val itemBinding: ItemTaskGroupEntryBinding
) : LifecycleViewHolder(itemBinding.root) {

    fun bind(taskGroupEntry: TaskGroupEntryItem) {
        itemBinding.apply {
            name = taskGroupEntry.name
            tasksCount = taskGroupEntry.tasksCount
            tasksDone = taskGroupEntry.tasksDone
        }
        itemBinding.entryContainer.setOnClickListener {
            onSelected(taskGroupEntry)
        }

        currentlySelectedItem.observe(this) {
            if (it == taskGroupEntry) {
                (itemBinding.entryContainer.background as TransitionDrawable).startTransition(
                    itemView.context.resources.getInteger(
                        R.integer.short_animation_duration
                    )
                )
            } else {
                (itemBinding.entryContainer.background as TransitionDrawable).resetTransition()
            }
        }
    }
}