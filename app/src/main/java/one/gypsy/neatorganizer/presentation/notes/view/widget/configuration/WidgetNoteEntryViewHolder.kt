package one.gypsy.neatorganizer.presentation.notes.view.widget.configuration

import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.WidgetItemNoteBinding
import one.gypsy.neatorganizer.presentation.common.LifecycleViewHolder
import one.gypsy.neatorganizer.presentation.notes.model.NoteEntryItem
import one.gypsy.neatorganizer.utils.extensions.fadeIn
import one.gypsy.neatorganizer.utils.extensions.hide

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
