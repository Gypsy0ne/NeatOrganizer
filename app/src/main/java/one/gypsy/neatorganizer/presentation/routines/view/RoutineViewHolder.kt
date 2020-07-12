package one.gypsy.neatorganizer.presentation.routines.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.presentation.common.AnimatableViewHolder
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

abstract class RoutineViewHolder(override val itemView: View) : RecyclerView.ViewHolder(itemView),
    AnimatableViewHolder {
    abstract fun bind(data: RoutineListItem)
}
