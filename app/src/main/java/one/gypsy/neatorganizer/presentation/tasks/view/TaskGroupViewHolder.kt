package one.gypsy.neatorganizer.presentation.tasks.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.binding.Bindable
import one.gypsy.neatorganizer.databinding.ItemTaskGroupBinding
import one.gypsy.neatorganizer.domain.dto.Task
import one.gypsy.neatorganizer.utils.LifecycleAware

class TaskGroupViewHolder(val itemBinding: ItemTaskGroupBinding) : RecyclerView.ViewHolder(itemBinding.root),
    LifecycleAware {

    override val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }


    fun bind(data: TaskListItem) {

    }
}