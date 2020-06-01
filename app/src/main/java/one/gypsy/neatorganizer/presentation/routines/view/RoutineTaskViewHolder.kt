package one.gypsy.neatorganizer.presentation.routines.view

import one.gypsy.neatorganizer.databinding.ItemRoutineTaskBinding
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

class RoutineTaskViewHolder(
    val itemBinding: ItemRoutineTaskBinding,
    val clickListener: SubItemClickListener<RoutineListItem.RoutineListSubItem>
) :
    RoutineViewHolder(itemBinding.root) {

    private lateinit var holderData: RoutineListItem.RoutineListSubItem

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListSubItem)
        holderData = data

        setUpEditListener()
        setUpEditionSubmitListener()
        setUpRemoveListener()
        setUpDoneListener()

        itemBinding.apply {
            routineTaskItem = data
            executePendingBindings()
        }
    }

    private fun setEditable(editable: Boolean) {
        itemBinding.editTextItemRoutineTaskName.apply {
            isFocusable = editable
            isFocusableInTouchMode = editable
            isEnabled = editable
            isClickable = editable
        }
        if (editable) {
            itemBinding.editTextItemRoutineTaskName.requestFocus()
        } else {
            itemBinding.editTextItemRoutineTaskName.clearFocus()
        }
    }

    private fun setUpEditListener() {
        itemBinding.setEditClickListener {
            holderData = holderData.copy(edited = !holderData.edited)
            setEditable(holderData.edited)
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