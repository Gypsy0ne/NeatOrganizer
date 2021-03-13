package one.gypsy.neatorganizer.note.view.widget.configuration

import android.view.View
import one.gypsy.neatorganizer.core.listing.LifecycleViewHolder
import one.gypsy.neatorganizer.note.model.WidgetNoteItem

internal abstract class WidgetNoteItemViewHolder(
    itemView: View
) : LifecycleViewHolder(itemView) {
    abstract fun bind(data: WidgetNoteItem)
}
