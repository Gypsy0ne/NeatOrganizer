package one.gypsy.neatorganizer.presentation.people.view

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R


class SwipeToUpdateInteractionCallback(var adapter: PeopleAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {


    private val updateIcon = adapter.context.getDrawable(R.drawable.ic_update_white_32dp)
    private val background: ColorDrawable =
        ColorDrawable(adapter.context.getColor(R.color.male_blue))


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.addInteractionEntry(viewHolder.adapterPosition)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        if (updateIcon != null) {
            val itemView: View = viewHolder.itemView
            val backgroundCornerOffset = 20
            val iconMargin: Int = (itemView.height - updateIcon.intrinsicHeight) / 2
            val iconTop: Int =
                itemView.top + (itemView.height - updateIcon.intrinsicHeight) / 2
            val iconBottom: Int = iconTop + updateIcon.intrinsicHeight

            if (dX > 0) { // Swiping to the right
                val iconLeft: Int = itemView.left + iconMargin + updateIcon.intrinsicWidth
                val iconRight: Int = itemView.left + iconMargin
                updateIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(
                    itemView.left, itemView.top,
                    itemView.left + dX.toInt() + backgroundCornerOffset,
                    itemView.bottom
                )
            } else if (dX < 0) { // Swiping to the left
                val iconLeft: Int = itemView.right - iconMargin - updateIcon.intrinsicWidth
                val iconRight: Int = itemView.right - iconMargin
                updateIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top, itemView.right, itemView.bottom
                )
            } else { // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }

            background.draw(c)
            updateIcon.draw(c)
        }
    }

}