package one.gypsy.neatorganizer.note.view.widget.configuration

import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.core.utils.extensions.fadeIn
import one.gypsy.neatorganizer.core.utils.extensions.hide
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.WidgetItemNoteBinding
import one.gypsy.neatorganizer.note.model.WidgetNoteItem

internal class WidgetNoteEntryViewHolder(
    private val itemBinding: WidgetItemNoteBinding,
    private val currentlySelectedItem: LiveData<WidgetNoteItem>,
    private val onSelected: (WidgetNoteItem) -> Unit
) : WidgetNoteItemViewHolder(itemBinding.root) {

    override fun bind(data: WidgetNoteItem) {
        (data as? WidgetNoteItem.EntryItem)?.let {
            itemBinding.apply {
                noteEntryItem = it
                onItemSelect = onSelected
                lifecycleOwner = this@WidgetNoteEntryViewHolder
            }
        }
    }

    override fun onAttached() {
        super.onAttached()
        currentlySelectedItem.observe(this) { animateItemSelection(it) }
    }

    private fun animateItemSelection(selectedItem: WidgetNoteItem) =
        if (selectedItem == itemBinding.noteEntryItem) {
            itemBinding.animateSelection()
        } else {
            itemBinding.selectionIndicator.hide()
        }

    private fun WidgetItemNoteBinding.animateSelection() {
        selectionIndicator.fadeIn()
        root.startAnimation(
            AnimationUtils.loadAnimation(
                itemBinding.root.context,
                R.anim.item_enlarge
            )
        )
    }
}
