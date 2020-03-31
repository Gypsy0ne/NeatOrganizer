package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.databinding.ItemTaskGroupBinding
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.presentation.tasks.vm.SingleTaskGroupViewModel

class GroupedTasksAdapter() : RecyclerView.Adapter<GroupedTasksAdapter.GroupedTasksViewHolder>(),
    BindableAdapter<List<SingleTaskGroup>> {

    var groupedTasks = mutableListOf<SingleTaskGroup>()


    override fun setData(dataCollection: List<SingleTaskGroup>) {
        //TODO add diff util here
        groupedTasks.clear()
        groupedTasks.addAll(dataCollection)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupedTasksViewHolder {
        val taskGroupDataBinding = DataBindingUtil.inflate<ItemTaskGroupBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_task_group,
            parent,
            false
        )
        return GroupedTasksViewHolder(taskGroupDataBinding)
    }

    override fun onViewAttachedToWindow(holder: GroupedTasksViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: GroupedTasksViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }

    override fun getItemCount(): Int {
        return groupedTasks.size
    }

    override fun onBindViewHolder(holder: GroupedTasksViewHolder, position: Int) {
        holder.bind(groupedTasks[position])
    }

    inner class GroupedTasksViewHolder(private val itemBinding: ItemTaskGroupBinding) :
        RecyclerView.ViewHolder(itemBinding.root), LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)

        init {
            lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        }

        fun markAttach() {
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
        }

        fun markDetach() {
            lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        }

        override fun getLifecycle(): Lifecycle {
            return lifecycleRegistry
        }

        fun bind(data: SingleTaskGroup) {
            itemBinding.apply {
                viewModel = SingleTaskGroupViewModel(data)
                lifecycleOwner = this@GroupedTasksViewHolder
                executePendingBindings()
            }
        }
    }
}
