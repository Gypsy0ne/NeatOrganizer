package one.gypsy.neatorganizer.note.view.widget.configuration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.core.binding.BindableAdapter
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.model.NoteEntryItem

internal class WidgetNoteEntriesAdapter(
    private val currentlySelectedItem: LiveData<NoteEntryItem>,
    private val onSelected: (NoteEntryItem) -> Unit
) : ListAdapter<NoteEntryItem,
    WidgetNoteEntryViewHolder>(DiffCallback()),
    BindableAdapter<NoteEntryItem> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetNoteEntryViewHolder =
        WidgetNoteEntryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.widget_item_note, parent, false
            ),
            currentlySelectedItem,
            onSelected
        )

    override fun onBindViewHolder(holder: WidgetNoteEntryViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun bindData(dataCollection: List<NoteEntryItem>) =
        submitList(dataCollection)

    override fun onViewAttachedToWindow(holder: WidgetNoteEntryViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttached()
    }

    override fun onViewDetachedFromWindow(holder: WidgetNoteEntryViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetached()
    }

    override fun onViewRecycled(holder: WidgetNoteEntryViewHolder) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteEntryItem>() {

        override fun areItemsTheSame(oldItem: NoteEntryItem, newItem: NoteEntryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntryItem, newItem: NoteEntryItem): Boolean {
            return oldItem == newItem
        }
    }
}
