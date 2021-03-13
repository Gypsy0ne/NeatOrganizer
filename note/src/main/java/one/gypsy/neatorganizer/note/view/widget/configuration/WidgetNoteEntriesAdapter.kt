package one.gypsy.neatorganizer.note.view.widget.configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.core.binding.BindableAdapter
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.model.WidgetNoteItem

internal class WidgetNoteEntriesAdapter(
    private val currentlySelectedItem: LiveData<WidgetNoteItem>,
    private val onSelected: (WidgetNoteItem) -> Unit,
    private val onCreate: () -> Unit = {}
) : ListAdapter<WidgetNoteItem, WidgetNoteItemViewHolder>(DiffCallback()),
    BindableAdapter<WidgetNoteItem> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetNoteItemViewHolder =
        WidgetItemViewType.values().first { it.resId == viewType }
            .getHolder(
                LayoutInflater.from(parent.context),
                parent,
                currentlySelectedItem,
                onSelected,
                onCreate
            )

    override fun onBindViewHolder(holder: WidgetNoteItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun bindData(dataCollection: List<WidgetNoteItem>) =
        submitList(dataCollection)

    override fun onViewAttachedToWindow(holder: WidgetNoteItemViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached()
    }

    override fun onViewDetachedFromWindow(holder: WidgetNoteItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    override fun onViewRecycled(holder: WidgetNoteItemViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].getViewHolderType()
    }

    class DiffCallback : DiffUtil.ItemCallback<WidgetNoteItem>() {

        override fun areItemsTheSame(
            oldItem: WidgetNoteItem,
            newItem: WidgetNoteItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WidgetNoteItem,
            newItem: WidgetNoteItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}

private enum class WidgetItemViewType(@LayoutRes val resId: Int) {
    ENTRY(R.layout.widget_item_note),
    FOOTER(R.layout.widget_item_footer)
}

private fun WidgetNoteItem.getViewHolderType(): Int = when (this) {
    is WidgetNoteItem.EntryItem -> WidgetItemViewType.ENTRY.resId
    is WidgetNoteItem.FooterItem -> WidgetItemViewType.FOOTER.resId
}

private fun WidgetItemViewType.getHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    currentlySelectedItem: LiveData<WidgetNoteItem>,
    onItemSelected: (WidgetNoteItem) -> Unit,
    onCreateClicked: () -> Unit
): WidgetNoteItemViewHolder = when (this) {
    WidgetItemViewType.ENTRY -> WidgetNoteEntryViewHolder(
        DataBindingUtil.inflate(inflater, resId, parent, false),
        currentlySelectedItem,
        onItemSelected
    )
    WidgetItemViewType.FOOTER -> WidgetNoteFooterViewHolder(
        DataBindingUtil.inflate(inflater, resId, parent, false),
        onCreateClicked
    )
}
