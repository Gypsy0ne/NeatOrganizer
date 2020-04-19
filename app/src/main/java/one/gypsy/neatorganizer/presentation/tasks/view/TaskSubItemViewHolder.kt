package one.gypsy.neatorganizer.presentation.tasks.view

import one.gypsy.neatorganizer.databinding.ItemTaskBinding
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskSubItemViewHolder(val itemBinding: ItemTaskBinding, val clickListener: ClickListener) :
    TaskViewHolder(itemBinding.root) {

    private lateinit var holderData: TaskListItem.TaskListSubItem

    interface ClickListener {
        fun onDoneClick(subItem: TaskListItem.TaskListSubItem)
        fun onEditionSubmitClick(subItem: TaskListItem.TaskListSubItem)
        fun onRemoveClick(subItem: TaskListItem.TaskListSubItem)
    }

    override fun bind(data: TaskListItem) {
        require(data is TaskListItem.TaskListSubItem)
        holderData = data

        setUpEditListener()
        setUpEditionSubmitListener()
        setUpRemoveListener()

        itemBinding.apply {
            taskSubItem = holderData
            executePendingBindings()
        }

    }

    private fun setEditable(editable: Boolean) {
        itemBinding.editTextItemTaskName.apply {
            isFocusable = editable
            isFocusableInTouchMode = editable
            isEnabled = editable
            isClickable = editable
        }
        if (editable) {
            itemBinding.editTextItemTaskName.requestFocus()
        } else {
            itemBinding.editTextItemTaskName.clearFocus()
        }
    }

    private fun setUpEditListener() {
        itemBinding.setEditClickListener {
            holderData = holderData.copy(edited = !holderData.edited)
            setEditable(holderData.edited)
            itemBinding.swipeLayoutItemTaskRoot.resetStatus()
        }
    }

    private fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            holderData = holderData.copy(
                name = itemBinding.editTextItemTaskName.text.toString()
            )
            clickListener.onEditionSubmitClick(holderData)
        }
    }

    private fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemTaskRoot.resetStatus()
            clickListener.onRemoveClick(holderData)
        }
    }
}