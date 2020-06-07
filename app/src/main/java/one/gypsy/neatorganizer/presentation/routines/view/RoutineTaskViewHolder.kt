package one.gypsy.neatorganizer.presentation.routines.view

import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.databinding.ItemRoutineTaskBinding
import one.gypsy.neatorganizer.presentation.listing.ListedSubItem
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.requestEdit
import one.gypsy.neatorganizer.utils.extensions.show

class RoutineTaskViewHolder(
    val itemBinding: ItemRoutineTaskBinding,
    val clickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
) : RoutineViewHolder(itemBinding.root), ListedSubItem<RoutineListItem.RoutineListSubItem> {

    override lateinit var viewData: RoutineListItem.RoutineListSubItem

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListSubItem)
        viewData = data

        updateEditable()
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

    override fun setUpSwipeMenuBehavior() {
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

    override fun clearEditionStatus() {
        viewData = viewData.copy(edited = false)
        updateEditable()
    }

    override fun updateEditable() {
        changeEditionAttributes()

        if (viewData.edited) {
            itemBinding.buttonItemRoutineTaskSubmit.show()
            itemBinding.checkBoxItemRoutineTaskDone.hide()
        } else {
            itemBinding.buttonItemRoutineTaskSubmit.hide()
            itemBinding.checkBoxItemRoutineTaskDone.show()
        }

        if (viewData.edited) {
            itemBinding.editTextItemRoutineTaskName.requestEdit()
        } else {
            itemBinding.editTextItemRoutineTaskName.clearFocus()
        }
    }

    private fun changeEditionAttributes() {
        itemBinding.editTextItemRoutineTaskName.apply {
            isFocusable = viewData.edited
            isFocusableInTouchMode = viewData.edited
            isEnabled = viewData.edited
            isClickable = viewData.edited
        }
    }


    override fun setUpEditListener() {
        itemBinding.setEditClickListener {
            viewData = viewData.copy(edited = !viewData.edited)
            updateEditable()
            itemBinding.swipeLayoutItemRoutineTaskRoot.resetStatus()
        }
    }

    override fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            viewData = viewData.copy(
                name = itemBinding.editTextItemRoutineTaskName.text.toString()
            )
            clickListener.onEditionSubmitClick(viewData)
        }
    }

    override fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemRoutineTaskRoot.resetStatus()
            clickListener.onRemoveClick(viewData)
        }
    }

    override fun setUpDoneListener() {
        itemBinding.setDoneClickListener {
            viewData = viewData.copy(
                done = !viewData.done
            )
            clickListener.onDoneClick(viewData)
        }
    }
}