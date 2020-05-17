package one.gypsy.neatorganizer.presentation.tasks.view


import android.view.View
import androidx.navigation.findNavController
import one.gypsy.neatorganizer.databinding.ItemTaskHeaderBinding
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskHeaderViewHolder(
    val itemBinding: ItemTaskHeaderBinding,
    val itemClickListener: ClickListener
) : TaskViewHolder(itemBinding.root) {

    lateinit var holderData: TaskListItem.TaskListHeader

    interface ClickListener {
        fun onExpanderClick(headerItem: TaskListItem.TaskListHeader)
        fun onEditionSubmitClick(headerItem: TaskListItem.TaskListHeader)
        fun onRemoveClick(headerItem: TaskListItem.TaskListHeader)
    }

    override fun bind(data: TaskListItem) {
        require(data is TaskListItem.TaskListHeader)
        holderData = data

        setUpAddListener()
        setUpEditListener()
        setUpEditionSubmitListener()
        setUpExpanderListener()
        setUpRemoveListener()

        itemBinding.apply {
            headerItem = holderData
            executePendingBindings()
        }
    }

    private fun navigateToAddTask(
        groupId: Long,
        view: View
    ) {
        val direction = TasksFragmentDirections.actionTasksToAddSingleTaskDialogFragment(groupId)
        view.findNavController().navigate(direction)
    }

    private fun setEditable(editable: Boolean) {
        itemBinding.editTextItemTaskHeaderName.apply {
            isFocusable = editable
            isFocusableInTouchMode = editable
            isEnabled = editable
            isClickable = editable
        }
        if (editable) {
            itemBinding.editTextItemTaskHeaderName.requestFocus()
        } else {
            itemBinding.editTextItemTaskHeaderName.clearFocus()
        }
    }

    private fun setUpExpanderListener() {
        itemBinding.setExpanderClickListener {
            holderData = holderData.copy(expanded = !holderData.expanded)
            itemClickListener.onExpanderClick(holderData)
        }
    }

    private fun setUpAddListener() {
        itemBinding.setAddClickListener {
            itemBinding.swipeLayoutItemTaskHeaderRoot.resetStatus()
            navigateToAddTask(holderData.id, itemBinding.root)
        }
    }

    private fun setUpEditListener() {
        itemBinding.setEditClickListener {
            holderData = holderData.copy(edited = !holderData.edited)
            setEditable(holderData.edited)
            itemBinding.swipeLayoutItemTaskHeaderRoot.resetStatus()
        }
    }

    private fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            holderData = holderData.copy(
                name = itemBinding.editTextItemTaskHeaderName.text.toString()
            )
            itemClickListener.onEditionSubmitClick(holderData)
        }
    }

    private fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemTaskHeaderRoot.resetStatus()
            itemClickListener.onRemoveClick(holderData)
        }
    }
}
