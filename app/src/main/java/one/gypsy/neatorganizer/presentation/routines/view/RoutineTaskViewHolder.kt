package one.gypsy.neatorganizer.presentation.routines.view

import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.databinding.ItemRoutineTaskBinding
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.requestEdit
import one.gypsy.neatorganizer.utils.extensions.show

class RoutineTaskViewHolder(
    val itemBinding: ItemRoutineTaskBinding,
    val clickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
) :
    RoutineViewHolder(itemBinding.root) {

    private lateinit var holderData: RoutineListItem.RoutineListSubItem

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListSubItem)
        holderData = data

        setUpSwipeMenuBehavior()
        setUpEditListener()
        setUpEditionSubmitListener()
        setUpRemoveListener()
        setUpDoneListener()

        itemBinding.apply {
            routineTaskItem = data
            executePendingBindings()
        }
    }

    private fun setUpSwipeMenuBehavior() {
        itemBinding.swipeLayoutItemRoutineTaskRoot.setMenuSwipeListener(object :
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
        itemBinding.editTextItemRoutineTaskName.apply {
            isFocusable = holderData.edited
            isFocusableInTouchMode = holderData.edited
            isEnabled = holderData.edited
            isClickable = holderData.edited
        }

        itemBinding.buttonItemRoutineTaskSubmit.apply {
            if (holderData.edited) {
                this.show()
            } else {
                this.hide()
            }
        }

        if (holderData.edited) {
            itemBinding.editTextItemRoutineTaskName.apply {
                requestEdit()
            }
        } else {
            itemBinding.editTextItemRoutineTaskName.clearFocus()
        }
    }


    private fun setUpEditListener() {
        itemBinding.setEditClickListener {
            holderData = holderData.copy(edited = !holderData.edited)
            updateEditable()
            itemBinding.swipeLayoutItemRoutineTaskRoot.resetStatus()
        }
    }

    private fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            holderData = holderData.copy(
                name = itemBinding.editTextItemRoutineTaskName.text.toString()
            )
            clickListener.onEditionSubmitClick(holderData)
        }
    }

    private fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemRoutineTaskRoot.resetStatus()
            clickListener.onRemoveClick(holderData)
        }
    }

    private fun setUpDoneListener() {
        itemBinding.setDoneClickListener {
            holderData = holderData.copy(
                done = !holderData.done
            )
            itemBinding.swipeLayoutItemRoutineTaskRoot.resetStatus()
            clickListener.onDoneClick(holderData)
        }
    }
}