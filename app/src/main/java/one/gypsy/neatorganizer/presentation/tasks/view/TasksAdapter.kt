package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class GroupedTasksAdapter(
    val headerClickListener: HeaderClickListener<TaskListItem.TaskListHeader>,
    val subItemClickListener: SubItemClickListener<TaskListItem.TaskListSubItem>
) : ListAdapter<TaskListItem, TaskViewHolder>(DiffCallback()), BindableAdapter<TaskListItem> {

    override fun bindData(dataCollection: List<TaskListItem>) {
        submitList(dataCollection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewType.values().first { it.resId == viewType }
            .getHolder(
                LayoutInflater.from(parent.context),
                parent,
                headerClickListener,
                subItemClickListener
            )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewHolderType()
    }

    class DiffCallback : DiffUtil.ItemCallback<TaskListItem>() {

        override fun areItemsTheSame(oldItem: TaskListItem, newItem: TaskListItem): Boolean {
            return oldItem.id == newItem.id && oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: TaskListItem, newItem: TaskListItem): Boolean {
            return oldItem == newItem
        }
    }
}

enum class TaskViewType(@LayoutRes val resId: Int) {
    HEADER(R.layout.item_task_header),
    SUB_ITEM(R.layout.item_task)
}

fun TaskListItem.getViewHolderType(): Int = when (this) {
    is TaskListItem.TaskListHeader -> TaskViewType.HEADER.resId
    is TaskListItem.TaskListSubItem -> TaskViewType.SUB_ITEM.resId
}

fun TaskViewType.getHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    headerClickListener: HeaderClickListener<TaskListItem.TaskListHeader>,
    subItemClickListener: SubItemClickListener<TaskListItem.TaskListSubItem>
): TaskViewHolder = when (this) {
    TaskViewType.HEADER -> TaskHeaderViewHolder(
        DataBindingUtil.inflate(
            inflater, resId, parent, false
        ), headerClickListener
    )
    TaskViewType.SUB_ITEM -> TaskSubItemViewHolder(
        DataBindingUtil.inflate(
            inflater,
            resId,
            parent,
            false
        ),
        subItemClickListener
    )
}

