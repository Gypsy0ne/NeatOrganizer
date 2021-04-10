package one.gypsy.neatorganizer.task.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.core.binding.BindableAdapter
import one.gypsy.neatorganizer.core.listing.HeaderClickListener
import one.gypsy.neatorganizer.core.listing.SubItemClickListener
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.model.TaskListItem

internal class GroupedTasksAdapter(
    private val headerClickListener: HeaderClickListener<TaskListItem.TaskListHeader>? = null,
    private val subItemClickListener: SubItemClickListener<TaskListItem.TaskListSubItem>? = null,
    private var onFirstHolderLayout: ((View) -> Unit)? = null
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
        onFirstHolderLayout?.let {
            it(holder.itemView)
            onFirstHolderLayout = null
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewHolderType()
    }

    class DiffCallback : DiffUtil.ItemCallback<TaskListItem>() {

        override fun areItemsTheSame(oldItem: TaskListItem, newItem: TaskListItem): Boolean {
            return oldItem.getViewHolderType() == newItem.getViewHolderType() && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskListItem, newItem: TaskListItem): Boolean {
            return oldItem == newItem
        }
    }
}

internal enum class TaskViewType(@LayoutRes val resId: Int) {
    HEADER(R.layout.item_task_header),
    SUB_ITEM(R.layout.item_task)
}

internal fun TaskListItem.getViewHolderType(): Int = when (this) {
    is TaskListItem.TaskListHeader -> TaskViewType.HEADER.resId
    is TaskListItem.TaskListSubItem -> TaskViewType.SUB_ITEM.resId
}

internal fun TaskViewType.getHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    headerClickListener: HeaderClickListener<TaskListItem.TaskListHeader>?,
    subItemClickListener: SubItemClickListener<TaskListItem.TaskListSubItem>?
): TaskViewHolder = when (this) {
    TaskViewType.HEADER -> TaskHeaderViewHolder(
        DataBindingUtil.inflate(
            inflater, resId, parent, false
        ),
        headerClickListener
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

interface HolderLayoutListener {
    val onFirstChildLayout: (View) -> Unit
}
