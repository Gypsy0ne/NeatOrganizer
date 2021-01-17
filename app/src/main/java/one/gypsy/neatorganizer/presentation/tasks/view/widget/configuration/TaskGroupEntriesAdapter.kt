package one.gypsy.neatorganizer.presentation.tasks.view.widget.configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem

class TaskGroupEntriesAdapter(
    private val currentlySelectedItem: LiveData<TaskGroupEntryItem>,
    private val onSelected: (TaskGroupEntryItem) -> Unit
) : ListAdapter<TaskGroupEntryItem, TaskGroupEntryViewHolder>(DiffCallback()),
    BindableAdapter<TaskGroupEntryItem> {

    override fun bindData(dataCollection: List<TaskGroupEntryItem>) = submitList(dataCollection)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskGroupEntryViewHolder =
        TaskGroupEntryViewHolder(
            currentlySelectedItem,
            onSelected,
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                R.layout.item_task_group_entry, parent, false
            )
        )

    override fun onBindViewHolder(holder: TaskGroupEntryViewHolder, position: Int) =
        holder.bind(getItem(position))

    // TODO extract binding logic from adapters
    override fun onViewAttachedToWindow(holder: TaskGroupEntryViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached()
    }

    override fun onViewDetachedFromWindow(holder: TaskGroupEntryViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    override fun onViewRecycled(holder: TaskGroupEntryViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    class DiffCallback : DiffUtil.ItemCallback<TaskGroupEntryItem>() {

        override fun areItemsTheSame(
            oldItem: TaskGroupEntryItem,
            newItem: TaskGroupEntryItem
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TaskGroupEntryItem,
            newItem: TaskGroupEntryItem
        ) = oldItem == newItem
    }
}
