package one.gypsy.neatorganizer.task.view

import android.view.LayoutInflater
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

class GroupedTasksAdapter(
    private val headerClickListener: HeaderClickListener<TaskListItem.TaskListHeader>? = null,
    private val subItemClickListener: SubItemClickListener<TaskListItem.TaskListSubItem>? = null
) : ListAdapter<TaskListItem, TaskViewHolder>(DiffCallback()), BindableAdapter<TaskListItem> {

    private var animateChanges = true

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
        with(holder) {
            bind(getItem(position))
            if (shouldAnimateChanges(this)) {
                animate()
            }
        }
        animateChanges = true
    }

    private fun shouldAnimateChanges(holder: TaskViewHolder) =
        holder.itemViewType != TaskViewType.HEADER.resId && animateChanges

    override fun onCurrentListChanged(
        previousList: MutableList<TaskListItem>,
        currentList: MutableList<TaskListItem>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        animateChanges = currentList.size != previousList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewHolderType()
    }

    override fun onViewDetachedFromWindow(holder: TaskViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.clearAnimation()
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
