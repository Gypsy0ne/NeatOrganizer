package one.gypsy.neatorganizer.presentation.notes.view.widget.configuration

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.databinding.WidgetItemNoteBinding
import one.gypsy.neatorganizer.presentation.common.LifecycleViewHolder
import one.gypsy.neatorganizer.presentation.notes.model.NoteEntryItem

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

    private fun LiveData<NoteEntryItem>.observeSelectionStatus() =
        observe(this@WidgetNoteEntryViewHolder) {
        }

//    private fun animateItemSelection(
//        selectedItem: TaskGroupEntryItem,
//        oldItem: TaskGroupEntryItem
//    ) = if (selectedItem == oldItem) {
//        (itemBinding.noteItemContainer. as TransitionDrawable).startTransition(
//            itemView.context.resources.getInteger(
//                R.integer.short_animation_duration
//            )
//        )
//    } else {
//        (itemBinding.entryContainer.background as TransitionDrawable).resetTransition()
//    }
}
