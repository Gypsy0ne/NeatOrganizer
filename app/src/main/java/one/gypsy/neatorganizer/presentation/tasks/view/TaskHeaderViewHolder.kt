package one.gypsy.neatorganizer.presentation.tasks.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.databinding.ItemTaskHeaderBinding
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.utils.LifecycleAware

class TaskHeaderViewHolder(val itemBinding: ItemTaskHeaderBinding) : TaskViewHolder(itemBinding.root) {

    override val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun bind(data: TaskListItem) {

    }
}