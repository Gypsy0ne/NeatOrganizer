package one.gypsy.neatorganizer.routine.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.routine.model.RoutineListItem

internal abstract class RoutineViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: RoutineListItem)
}
