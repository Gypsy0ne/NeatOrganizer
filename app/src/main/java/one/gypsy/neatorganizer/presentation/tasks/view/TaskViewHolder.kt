package one.gypsy.neatorganizer.presentation.tasks.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.databinding.ItemGroupedTaskBinding
import one.gypsy.neatorganizer.binding.Bindable
import one.gypsy.neatorganizer.utils.LifecycleAware

class TaskViewHolder(val itemBinding: ItemGroupedTaskBinding) : RecyclerView.ViewHolder(itemBinding.root), LifecycleAware {
    override val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun bind(data: TaskListItem) {
    }
}
