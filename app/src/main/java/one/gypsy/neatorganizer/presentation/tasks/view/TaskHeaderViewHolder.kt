package one.gypsy.neatorganizer.presentation.tasks.view


import androidx.navigation.findNavController
import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.databinding.ItemTaskHeaderBinding
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.ListedHeader
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.requestEdit
import one.gypsy.neatorganizer.utils.extensions.show

class TaskHeaderViewHolder(
    val itemBinding: ItemTaskHeaderBinding,
    val clickListener: HeaderClickListener<TaskListItem.TaskListHeader>
) : TaskViewHolder(itemBinding.root), ListedHeader<TaskListItem.TaskListHeader> {

    override lateinit var viewData: TaskListItem.TaskListHeader

    override fun bind(data: TaskListItem) {
        require(data is TaskListItem.TaskListHeader)
        viewData = data

        updateEditable()
        setUpSwipeMenuBehavior()
        setUpAddListener()
        setUpEditListener()
        setUpEditionSubmitListener()
        setUpExpanderListener()
        setUpRemoveListener()

        bindInitially()
    }

    private fun bindInitially() {
        itemBinding.apply {
            headerItem = viewData
            animateChanges = false
            executePendingBindings()
        }
    }

    override fun updateEditable() {
        changeNameEditionAttributes()
        if (viewData.edited) {
            onEditStart()
        } else {
            onEditFinish()
        }
    }

    private fun onEditFinish() {
        itemBinding.buttonItemTaskHeaderSubmit.hide()
        itemBinding.buttonItemTaskHeaderExpand.show()
        itemBinding.editTextItemTaskHeaderName.clearFocus()
    }

    private fun onEditStart() {
        itemBinding.buttonItemTaskHeaderSubmit.show()
        itemBinding.buttonItemTaskHeaderExpand.hide()
        itemBinding.editTextItemTaskHeaderName.requestEdit()
    }

    private fun changeNameEditionAttributes() {
        itemBinding.editTextItemTaskHeaderName.apply {
            isFocusable = viewData.edited
            isFocusableInTouchMode = viewData.edited
            isEnabled = viewData.edited
            isClickable = viewData.edited
        }
    }

    override fun setUpSwipeMenuBehavior() {
        itemBinding.swipeLayoutItemTaskHeaderRoot.setMenuSwipeListener(object :
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
            itemBinding.swipeLayoutItemTaskHeaderRoot.resetStatus()
            navigateToAddTask(viewData.id)
        }
    }

    private fun navigateToAddTask(groupId: Long) {
        val direction = TasksFragmentDirections.actionTasksToAddSingleTaskDialogFragment(groupId)
        itemBinding.root.findNavController().navigate(direction)
    }

    override fun setUpEditListener() {
        itemBinding.setEditClickListener {
            viewData = viewData.copy(edited = !viewData.edited)
            updateEditable()
            itemBinding.swipeLayoutItemTaskHeaderRoot.resetStatus()
        }
    }

    override fun setUpEditionSubmitListener() {
        itemBinding.setEditionSubmitClickListener {
            if (didItemNameChange()) {
                viewData = viewData.copy(
                    name = itemBinding.editTextItemTaskHeaderName.text.toString()
                )
                clickListener.onEditionSubmitClick(viewData)
            } else {
                clearEditionStatus()
            }
        }
    }

    private fun didItemNameChange() =
        viewData.name != itemBinding.editTextItemTaskHeaderName.text.toString()

    override fun setUpRemoveListener() {
        itemBinding.setRemoveClickListener {
            itemBinding.swipeLayoutItemTaskHeaderRoot.resetStatus()
            clickListener.onRemoveClick(viewData)
        }
    }
}
