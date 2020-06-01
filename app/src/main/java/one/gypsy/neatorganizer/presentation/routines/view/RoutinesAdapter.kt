package one.gypsy.neatorganizer.presentation.routines.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

class RoutinesAdapter(
    val headerClickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>,
    val subItemClickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
) : ListAdapter<RoutineListItem, RoutineViewHolder>(DiffCallback()),
    BindableAdapter<RoutineListItem> {

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
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewHolderType()
    }

    class DiffCallback : DiffUtil.ItemCallback<RoutineListItem>() {

        override fun areItemsTheSame(oldItem: RoutineListItem, newItem: RoutineListItem): Boolean {
            return oldItem.id == newItem.id && oldItem.equals(newItem)
        }

        override fun areContentsTheSame(
            oldItem: RoutineListItem,
            newItem: RoutineListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}

enum class RoutineViewType(@LayoutRes val resId: Int) {
    HEADER(R.layout.item_routine_header),
    SUB_ITEM(R.layout.item_routine_task)
}

fun RoutineListItem.getViewHolderType(): Int = when (this) {
    is RoutineListItem.RoutineListHeader -> RoutineViewType.HEADER.resId
    is RoutineListItem.RoutineListSubItem -> RoutineViewType.SUB_ITEM.resId
}

fun RoutineViewType.getHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    headerClickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>,
    subItemClickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
) = when (this) {
    RoutineViewType.HEADER -> RoutineHeaderViewHolder(
        DataBindingUtil.inflate(
            inflater, resId, parent, false
        ), headerClickListener
    )
    RoutineViewType.SUB_ITEM -> RoutineTaskViewHolder(
        DataBindingUtil.inflate(
            inflater,
            resId,
            parent,
            false
        ), subItemClickListener
    )
}

