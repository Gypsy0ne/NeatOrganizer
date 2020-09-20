package one.gypsy.neatorganizer.presentation.tasks.view.widget


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.presentation.tasks.model.TaskGroupEntryItem

class TaskGroupEntriesAdapter :
    ListAdapter<TaskGroupEntryItem, TaskGroupEntryViewHolder>(DiffCallback()),
    BindableAdapter<TaskGroupEntryItem> {

    override fun bindData(dataCollection: List<TaskGroupEntryItem>) {
        submitList(dataCollection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskGroupEntryViewHolder =
        TaskGroupEntryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ), R.layout.item_task_group_entry, parent, false
            )
        )

    override fun onBindViewHolder(holder: TaskGroupEntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<TaskGroupEntryItem>() {

        override fun areItemsTheSame(
            oldItem: TaskGroupEntryItem,
            newItem: TaskGroupEntryItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TaskGroupEntryItem,
            newItem: TaskGroupEntryItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
