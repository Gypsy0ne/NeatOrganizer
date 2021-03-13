package one.gypsy.neatorganizer.note.view.widget.configuration

import one.gypsy.neatorganizer.note.databinding.WidgetItemFooterBinding
import one.gypsy.neatorganizer.note.model.WidgetNoteItem

internal class WidgetNoteFooterViewHolder(
    private val itemBinding: WidgetItemFooterBinding,
    private val onCreateClicked: () -> Unit
) : WidgetNoteItemViewHolder(itemBinding.root) {

    override fun bind(data: WidgetNoteItem) {
        itemBinding.apply {
            lifecycleOwner = this@WidgetNoteFooterViewHolder
            createNote.setOnClickListener { onCreateClicked() }
        }
    }
}
