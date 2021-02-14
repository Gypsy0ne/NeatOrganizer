package one.gypsy.neatorganizer.note.view.widget.configuration

import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.core.listing.LifecycleViewHolder
import one.gypsy.neatorganizer.core.utils.extensions.fadeIn
import one.gypsy.neatorganizer.core.utils.extensions.hide
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.WidgetItemNoteBinding
import one.gypsy.neatorganizer.note.model.NoteEntryItem

class WidgetNoteEntryViewHolder(
    private val itemBinding: WidgetItemNoteBinding,
    private val currentlySelectedItem: LiveData<NoteEntryItem>,
    private val onSelected: (NoteEntryItem) -> Unit
) : LifecycleViewHolder(itemBinding.root) {

    fun bind(data: NoteEntryItem) {
        itemBinding.noteEntryItem = data
        // TODO do the same on regular list with notes
        itemBinding.onItemSelect = onSelected
    }

    override fun onAttached() {
        super.onAttached()
        currentlySelectedItem.observe(this) { animateItemSelection(it) }
    }

    private fun animateItemSelection(
        selectedItem: NoteEntryItem,
    ) = if (selectedItem == itemBinding.noteEntryItem) {
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
