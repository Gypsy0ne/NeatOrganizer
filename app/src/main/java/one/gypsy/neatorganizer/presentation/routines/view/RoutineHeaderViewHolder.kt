package one.gypsy.neatorganizer.presentation.routines.view

import androidx.navigation.findNavController
import one.gypsy.neatorganizer.databinding.ItemRoutineHeaderBinding
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

class RoutineHeaderViewHolder(
    val itemBinding: ItemRoutineHeaderBinding,
    val clickListener: ClickListener
) :
    RoutineViewHolder(itemBinding.root) {

    private lateinit var holderData: RoutineListItem.RoutineListHeader

    interface ClickListener {
        fun onExpanderClick(headerItem: RoutineListItem.RoutineListHeader)
        fun onEditionSubmitClick(headerItem: RoutineListItem.RoutineListHeader)
        fun onRemoveClick(headerItem: RoutineListItem.RoutineListHeader)
    }

    override fun bind(data: RoutineListItem) {
        require(data is RoutineListItem.RoutineListHeader)
        with(data) {
            holderData = this

            setUpAddListener()
            setUpExpanderListener()
            setUpEditListener()
            setUpEditionSubmitListener()
            setUpRemoveListener()

            itemBinding.apply {
                headerItem = this@with
                executePendingBindings()
            }
        }
    }

    private fun navigateToAddRoutineTask(routineId: Long) {
        val direction =
            RoutinesFragmentDirections.actionRoutinesToAddRoutineTaskDialogFragment(routineId)
        itemBinding.root.findNavController().navigate(direction)
    }

    private fun setEditable(editable: Boolean) {
        itemBinding.editTextItemRoutineHeaderName.apply {
            isFocusable = editable
            isFocusableInTouchMode = editable
            isEnabled = editable
            isClickable = editable
        }
        if (editable) {
            itemBinding.editTextItemRoutineHeaderName.requestFocus()
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
            setEditable(holderData.edited)
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