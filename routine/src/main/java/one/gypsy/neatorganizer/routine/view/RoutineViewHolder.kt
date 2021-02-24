package one.gypsy.neatorganizer.routine.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.core.listing.AnimatableViewHolder
import one.gypsy.neatorganizer.routine.model.RoutineListItem

internal abstract class RoutineViewHolder(override val itemView: View) :
    RecyclerView.ViewHolder(itemView), AnimatableViewHolder {
    abstract fun bind(data: RoutineListItem)
}
