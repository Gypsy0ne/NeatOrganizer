package one.gypsy.neatorganizer.routine.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.core.binding.BindableAdapter
import one.gypsy.neatorganizer.core.listing.HeaderClickListener
import one.gypsy.neatorganizer.core.listing.SubItemClickListener
import one.gypsy.neatorganizer.routine.R
import one.gypsy.neatorganizer.routine.model.RoutineListItem

internal class RoutinesAdapter(
    val headerClickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>,
    val subItemClickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
) : ListAdapter<RoutineListItem, RoutineViewHolder>(DiffCallback()),
    BindableAdapter<RoutineListItem> {

    private var animateChanges = true

    override fun bindData(dataCollection: List<RoutineListItem>) {
        submitList(dataCollection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder =
        RoutineViewType.values().first { it.resId == viewType }
            .getHolder(
                LayoutInflater.from(parent.context),
                parent,
                headerClickListener,
                subItemClickListener
            )

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (holder.itemViewType != RoutineViewType.HEADER.resId && animateChanges) {
            holder.animate()
        }
        animateChanges = true
    }

    override fun onCurrentListChanged(
        previousList: MutableList<RoutineListItem>,
        currentList: MutableList<RoutineListItem>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        animateChanges = previousList.size != currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewHolderType()
    }

    override fun onViewDetachedFromWindow(holder: RoutineViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.clearAnimation()
    }

    class DiffCallback : DiffUtil.ItemCallback<RoutineListItem>() {

        override fun areItemsTheSame(oldItem: RoutineListItem, newItem: RoutineListItem): Boolean {
            return oldItem.getViewHolderType() == newItem.getViewHolderType() && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RoutineListItem,
            newItem: RoutineListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}

internal enum class RoutineViewType(@LayoutRes val resId: Int) {
    HEADER(R.layout.item_routine_header),
    SUB_ITEM(R.layout.item_routine_task)
}

internal fun RoutineListItem.getViewHolderType(): Int = when (this) {
    is RoutineListItem.RoutineListHeader -> RoutineViewType.HEADER.resId
    is RoutineListItem.RoutineListSubItem -> RoutineViewType.SUB_ITEM.resId
}

internal fun RoutineViewType.getHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    headerClickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>,
    subItemClickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
): RoutineViewHolder = when (this) {
    RoutineViewType.HEADER -> RoutineHeaderViewHolder(
        DataBindingUtil.inflate(
            inflater, resId, parent, false
        ),
        headerClickListener
    )
    RoutineViewType.SUB_ITEM -> RoutineTaskViewHolder(
        DataBindingUtil.inflate(
            inflater,
            resId,
            parent,
            false
        ),
        subItemClickListener
    )
}
