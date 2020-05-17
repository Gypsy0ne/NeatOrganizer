package one.gypsy.neatorganizer.presentation.routines.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

abstract class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: RoutineListItem)
}
