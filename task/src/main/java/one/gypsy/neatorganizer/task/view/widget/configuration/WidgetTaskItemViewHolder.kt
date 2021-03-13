package one.gypsy.neatorganizer.task.view.widget.configuration

import android.view.View
import one.gypsy.neatorganizer.core.listing.LifecycleViewHolder
import one.gypsy.neatorganizer.task.model.WidgetTaskGroupItem

internal abstract class WidgetTaskItemViewHolder(
    itemView: View
) : LifecycleViewHolder(itemView) {
    abstract fun bind(data: WidgetTaskGroupItem)
}
