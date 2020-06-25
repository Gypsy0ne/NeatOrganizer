package one.gypsy.neatorganizer.presentation.routines.view

import androidx.navigation.findNavController
import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.databinding.ItemRoutineHeaderBinding
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.ListedHeader
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.requestEdit
import one.gypsy.neatorganizer.utils.extensions.show

class RoutineHeaderViewHolder(
    val itemBinding: ItemRoutineHeaderBinding,
    val clickListener: HeaderClickListener<RoutineListItem.RoutineListHeader>
) : RoutineViewHolder(itemBinding.root), ListedHeader<RoutineListItem.RoutineListHeader> {

    override lateinit var viewData: RoutineListItem.RoutineListHeader

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListHeader)
        viewData = data

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

    override fun updateEditable() {
        changeNameEditionAttributes()
        itemBinding.dayPickerItemRoutineHeaderSchedule.interactable = viewData.edited
        if (viewData.edited) {
            onEditStart()
        } else {
            onEditFinish()
        }
    }

    private fun onEditFinish() {
        itemBinding.buttonItemRoutineHeaderSubmit.hide()
        itemBinding.buttonItemRoutineHeaderExpand.show()
        itemBinding.editTextItemRoutineHeaderName.clearFocus()
    }

    private fun onEditStart() {
        itemBinding.buttonItemRoutineHeaderSubmit.show()
        itemBinding.buttonItemRoutineHeaderExpand.hide()
        itemBinding.editTextItemRoutineHeaderName.requestEdit()
    }

    private fun changeNameEditionAttributes() {
        itemBinding.editTextItemRoutineHeaderName.apply {
            isFocusable = viewData.edited
            isFocusableInTouchMode = viewData.edited
            isEnabled = viewData.edited
            isClickable = viewData.edited
        }
    }

    override fun setUpSwipeMenuBehavior() {
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

    override fun clearEditionStatus() {
        viewData = viewData.copy(edited = false)
        updateEditable()
    }

    override fun setUpExpanderListener() {
        itemBinding.setExpanderClickListener {
            viewData = viewData.copy(expanded = !viewData.expanded)
            clickListener.onExpanderClick(viewData)
        }
    }

    override fun setUpAddListener() {
        itemBinding.setAddClickListener {
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
            navigateToAddRoutineTask(viewData.id)
        }
    }

    private fun navigateToAddRoutineTask(routineId: Long) {
        val direction =
            RoutinesFragmentDirections.actionRoutinesToAddRoutineTaskDialogFragment(routineId)
        itemBinding.root.findNavController().navigate(direction)
    }

    override fun setUpEditListener() {
        itemBinding.setEditClickListener {
            viewData = viewData.copy(edited = !viewData.edited)
            updateEditable()
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
        }
    }

    override fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            viewData = viewData.copy(
                name = itemBinding.editTextItemRoutineHeaderName.text.toString(),
                scheduleDays = itemBinding.dayPickerItemRoutineHeaderSchedule.scheduleDaysStatus,
                edited = false
            )
            updateEditable()
            clickListener.onEditionSubmitClick(viewData)
        }
    }

    override fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemRoutineHeaderRoot.resetStatus()
            clickListener.onRemoveClick(viewData)
        }
    }

}