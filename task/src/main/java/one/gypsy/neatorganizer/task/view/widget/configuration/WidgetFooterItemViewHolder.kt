package one.gypsy.neatorganizer.task.view.widget.configuration

import one.gypsy.neatorganizer.task.databinding.WidgetItemCreateFooterBinding
import one.gypsy.neatorganizer.task.model.WidgetTaskGroupItem

internal class WidgetFooterItemViewHolder(
    private val onCreate: () -> Unit,
    private val itemBinding: WidgetItemCreateFooterBinding
) : WidgetTaskItemViewHolder(itemBinding.root) {

    override fun bind(data: WidgetTaskGroupItem) {
        itemBinding.apply {
            lifecycleOwner = this@WidgetFooterItemViewHolder
            createTaskGroup.setOnClickListener { onCreate() }
        }
    }
}
