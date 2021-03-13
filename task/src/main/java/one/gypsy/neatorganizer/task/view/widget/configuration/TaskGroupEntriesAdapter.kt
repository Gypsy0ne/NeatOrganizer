package one.gypsy.neatorganizer.task.view.widget.configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.core.binding.BindableAdapter
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.model.WidgetTaskGroupItem

internal class TaskGroupEntriesAdapter(
    private val currentlySelectedItem: LiveData<WidgetTaskGroupItem>,
    private val onSelected: (WidgetTaskGroupItem) -> Unit,
    private val onCreate: () -> Unit = {}
) : ListAdapter<WidgetTaskGroupItem, WidgetTaskItemViewHolder>(DiffCallback()),
    BindableAdapter<WidgetTaskGroupItem> {

    override fun bindData(dataCollection: List<WidgetTaskGroupItem>) = submitList(dataCollection)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetTaskItemViewHolder =
        WidgetItemViewType.values().first { it.resId == viewType }
            .getHolder(
                LayoutInflater.from(parent.context),
                parent,
                currentlySelectedItem,
                onSelected,
                onCreate
            )

    override fun onBindViewHolder(holder: WidgetTaskItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onViewAttachedToWindow(holder: WidgetTaskItemViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached()
    }

    override fun onViewDetachedFromWindow(holder: WidgetTaskItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    override fun onViewRecycled(holder: WidgetTaskItemViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].getViewHolderType()
    }

    class DiffCallback : DiffUtil.ItemCallback<WidgetTaskGroupItem>() {

        override fun areItemsTheSame(
            oldItem: WidgetTaskGroupItem,
            newItem: WidgetTaskGroupItem
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: WidgetTaskGroupItem,
            newItem: WidgetTaskGroupItem
        ) = oldItem == newItem
    }
}

private enum class WidgetItemViewType(@LayoutRes val resId: Int) {
    ENTRY(R.layout.widget_item_group_entry),
    FOOTER(R.layout.widget_item_create_footer)
}

private fun WidgetTaskGroupItem.getViewHolderType(): Int = when (this) {
    is WidgetTaskGroupItem.Entry -> WidgetItemViewType.ENTRY.resId
    is WidgetTaskGroupItem.Footer -> WidgetItemViewType.FOOTER.resId
}

private fun WidgetItemViewType.getHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    currentlySelectedItem: LiveData<WidgetTaskGroupItem>,
    onItemSelected: (WidgetTaskGroupItem) -> Unit,
    onCreateClicked: () -> Unit
): WidgetTaskItemViewHolder = when (this) {
    WidgetItemViewType.ENTRY -> WidgetTaskGroupEntryViewHolder(
        currentlySelectedItem,
        onItemSelected,
        DataBindingUtil.inflate(
            inflater, resId, parent, false
        )
    )
    WidgetItemViewType.FOOTER -> WidgetFooterItemViewHolder(
        onCreateClicked,
        DataBindingUtil.inflate(
            inflater,
            resId,
            parent,
            false
        )
    )
}
