package one.gypsy.neatorganizer.task.view.widget.configuration

import android.graphics.drawable.TransitionDrawable
import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.databinding.WidgetItemGroupEntryBinding
import one.gypsy.neatorganizer.task.model.WidgetTaskGroupItem

internal class WidgetTaskGroupEntryViewHolder(
    private val currentlySelectedItem: LiveData<WidgetTaskGroupItem>,
    private val onSelected: (WidgetTaskGroupItem) -> Unit,
    private val itemBinding: WidgetItemGroupEntryBinding
) : WidgetTaskItemViewHolder(itemBinding.root) {

    override fun bind(data: WidgetTaskGroupItem) {
        (data as? WidgetTaskGroupItem.Entry)?.let { entry ->
            itemBinding.widgetGroupEntry = entry
            itemBinding.entryContainer.setOnClickListener {
                onSelected(entry)
            }
        }
    }

    override fun onAttached() {
        super.onAttached()
        currentlySelectedItem.observe(this) { animateItemSelection(it) }
    }

    private fun animateItemSelection(
        selectedItem: WidgetTaskGroupItem,
    ) = if (selectedItem == itemBinding.widgetGroupEntry) {
        (itemBinding.entryContainer.background as TransitionDrawable).startTransition(
            itemView.context.resources.getInteger(
                R.integer.short_animation_duration
            )
        )
    } else {
        (itemBinding.entryContainer.background as TransitionDrawable).resetTransition()
    }
}
