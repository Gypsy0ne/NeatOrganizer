package one.gypsy.neatorganizer.presentation.tasks.view.widget.configuration

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

    // TODO this field might be kept within the binding
    private lateinit var itemEntry: TaskGroupEntryItem

    fun bind(taskGroupEntry: TaskGroupEntryItem) {
        itemEntry = taskGroupEntry
        itemBinding.apply {
            name = itemEntry.name
            tasksCount = itemEntry.tasksCount
            tasksDone = itemEntry.tasksDone
            entryContainer.setOnClickListener {
                onSelected(itemEntry)
            }
        }
    }

    override fun onAttached() {
        super.onAttached()
        currentlySelectedItem.observe(this) { animateItemSelection(it) }
    }

    private fun animateItemSelection(
        selectedItem: TaskGroupEntryItem,
    ) = if (selectedItem == itemEntry) {
        (itemBinding.entryContainer.background as TransitionDrawable).startTransition(
            itemView.context.resources.getInteger(
                R.integer.short_animation_duration
            )
        )
    } else {
        (itemBinding.entryContainer.background as TransitionDrawable).resetTransition()
    }
}
