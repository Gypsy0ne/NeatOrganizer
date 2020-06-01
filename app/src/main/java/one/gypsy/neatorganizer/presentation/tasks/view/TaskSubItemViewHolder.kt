package one.gypsy.neatorganizer.presentation.tasks.view

import one.gypsy.neatorganizer.databinding.ItemTaskBinding
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskSubItemViewHolder(
    val itemBinding: ItemTaskBinding,
    val clickListener: SubItemClickListener<TaskListItem.TaskListSubItem>
) :
    TaskViewHolder(itemBinding.root) {

    private lateinit var holderData: TaskListItem.TaskListSubItem

    override fun bind(data: TaskListItem) {
        require(data is TaskListItem.TaskListSubItem)
        holderData = data

        setUpEditListener()
        setUpEditionSubmitListener()
        setUpRemoveListener()
        setUpDoneListener()

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

    private fun setUpDoneListener() {
        itemBinding.setDoneClickListener {
            holderData = holderData.copy(
                done = !holderData.done
            )
            itemBinding.swipeLayoutItemTaskRoot.resetStatus()
            clickListener.onDoneClick(holderData)
        }
    }
}