package one.gypsy.neatorganizer.presentation.tasks.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import one.gypsy.neatorganizer.databinding.ItemTaskBinding
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskSubItemViewHolder(val itemBinding: ItemTaskBinding) : TaskViewHolder(itemBinding.root) {

    override val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun bind(data: TaskListItem) {
    }

}