package one.gypsy.neatorganizer.presentation.routines.view

import androidx.navigation.findNavController
import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.databinding.ItemRoutineHeaderBinding
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.requestEdit
import one.gypsy.neatorganizer.utils.extensions.show

class RoutineHeaderViewHolder(
    val itemBinding: ItemRoutineHeaderBinding,
    val clickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>
) :
    RoutineViewHolder(itemBinding.root) {

    private lateinit var holderData: RoutineListItem.RoutineListHeader

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListHeader)
        holderData = data

        updateEditable()
        setUpSwipeMenuBehavior()
        setUpAddListener()
        setUpExpanderListener()
        setUpEditListener()
        setUpEditionSubmitListener()
        setUpRemoveListener()

        itemBinding.apply {
            headerItem = data
            executePendingBindings()
        }
    }

    private fun navigateToAddRoutineTask(routineId: Long) {
        val direction =
            RoutinesFragmentDirections.actionRoutinesToAddRoutineTaskDialogFragment(routineId)
        itemBinding.root.findNavController().navigate(direction)
    }

    private fun setUpSwipeMenuBehavior() {
        itemBinding.swipeLayoutItemRoutineHeaderRoot.setMenuSwipeListener(object :
            SwipeMenuListener {
            override fun onLeftMenuOpen() {
                clearEditionStatus()
            }

            override fun onRightMenuOpen() {
                clearEditionStatus()
            }
        })
    }

    private fun clearEditionStatus() {
        holderData = holderData.copy(edited = false)
        updateEditable()
    }

    private fun updateEditable() {
        itemBinding.editTextItemRoutineHeaderName.apply {
            isFocusable = holderData.edited
            isFocusableInTouchMode = holderData.edited
            isEnabled = holderData.edited
            isClickable = holderData.edited
        }

        itemBinding.buttonItemRoutineHeaderSubmit.apply {
            if (holderData.edited) {
                this.show()
            } else {
                this.hide()
            }
        }

        if (holderData.edited) {
            itemBinding.editTextItemRoutineHeaderName.apply {
                requestEdit()
            }
        } else {
            itemBinding.editTextItemRoutineHeaderName.clearFocus()
        }
    }

    private fun setUpExpanderListener() {
        itemBinding.setExpanderClickListener {
            holderData = holderData.copy(expanded = !holderData.expanded)
            clickListener.onExpanderClick(holderData)
        }
    }

    private fun setUpAddListener() {
        itemBinding.setAddClickListener {
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
            navigateToAddRoutineTask(holderData.id)
        }
    }

    private fun setUpEditListener() {
        itemBinding.setEditClickListener {
            holderData = holderData.copy(edited = !holderData.edited)
            updateEditable()
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
        }
    }

    private fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            holderData = holderData.copy(
                name = itemBinding.editTextItemRoutineHeaderName.text.toString()
            )
            clickListener.onEditionSubmitClick(holderData)
        }
    }

    private fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
            clickListener.onRemoveClick(holderData)
        }
    }
}