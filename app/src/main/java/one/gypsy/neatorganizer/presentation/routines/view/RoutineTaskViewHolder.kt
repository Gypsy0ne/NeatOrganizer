package one.gypsy.neatorganizer.presentation.routines.view

import one.gypsy.neatorganizer.databinding.ItemRoutineTaskBinding
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

class RoutineTaskViewHolder(
    val itemBinding: ItemRoutineTaskBinding,
    val clickListener: ClickListener
) :
    RoutineViewHolder(itemBinding.root) {

    private lateinit var holderData: RoutineListItem.RoutineListSubItem

    interface ClickListener {
        fun onDoneClick(subItem: RoutineListItem.RoutineListSubItem)
        fun onEditionSubmitClick(subItem: RoutineListItem.RoutineListSubItem)
        fun onRemoveClick(subItem: RoutineListItem.RoutineListSubItem)
    }

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListSubItem)
        holderData = data

//        setUpEditListener()
//        setUpEditionSubmitListener()
//        setUpRemoveListener()
//        setUpDoneListener()

//        itemBinding.apply {
//            taskSubItem = holderData
//            executePendingBindings()
//        }

    }

//    private fun setEditable(editable: Boolean) {
//        itemBinding.editTextItemTaskName.apply {
//            isFocusable = editable
//            isFocusableInTouchMode = editable
//            isEnabled = editable
//            isClickable = editable
//        }
//        if (editable) {
//            itemBinding.editTextItemTaskName.requestFocus()
//        } else {
//            itemBinding.editTextItemTaskName.clearFocus()
//        }
//    }
//
//    private fun setUpEditListener() {
//        itemBinding.setEditClickListener {
//            holderData = holderData.copy(edited = !holderData.edited)
//            setEditable(holderData.edited)
//            itemBinding.swipeLayoutItemTaskRoot.resetStatus()
//        }
//    }
//
//    private fun setUpEditionSubmitListener() {
//        itemBinding.setEditionSubmitClickListener {
//            holderData = holderData.copy(
//                name = itemBinding.editTextItemTaskName.text.toString()
//            )
//            clickListener.onEditionSubmitClick(holderData)
//        }
//    }
//
//    private fun setUpRemoveListener() {
//        itemBinding.setRemoveClickListener {
//            itemBinding.swipeLayoutItemTaskRoot.resetStatus()
//            clickListener.onRemoveClick(holderData)
//        }
//    }
//
//    private fun setUpDoneListener() {
//        itemBinding.setDoneClickListener {
//            holderData = holderData.copy(
//                done = !holderData.done
//            )
//            itemBinding.swipeLayoutItemTaskRoot.resetStatus()
//            clickListener.onDoneClick(holderData)
//        }
//    }
}