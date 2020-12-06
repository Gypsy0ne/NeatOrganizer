package one.gypsy.neatorganizer.presentation.tasks.view

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.guanaj.easyswipemenulibrary.SwipeMenuListener
import one.gypsy.neatorganizer.binding.setEditionEnabled
import one.gypsy.neatorganizer.databinding.ItemTaskHeaderBinding
import one.gypsy.neatorganizer.presentation.common.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.common.listing.ListedHeader
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.show

class TaskHeaderViewHolder(
    private val itemBinding: ItemTaskHeaderBinding,
    private val clickListener: HeaderClickListener<TaskListItem.TaskListHeader>? = null
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

    private fun bindInitially() =
        itemBinding.apply {
            headerItem = viewData
            animateChanges = false
            executePendingBindings()
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
    }

    private fun onEditStart() {
        itemBinding.buttonItemTaskHeaderSubmit.show()
        itemBinding.buttonItemTaskHeaderExpand.hide()
    }

    private fun changeNameEditionAttributes() =
        setEditionEnabled(itemBinding.editTextItemTaskHeaderName, viewData.edited)

    override fun setUpSwipeMenuBehavior() =
        itemBinding.swipeLayoutItemTaskHeaderRoot.setMenuSwipeListener(object :
                SwipeMenuListener {
                override fun onLeftMenuOpen() {
                    clearEditionStatus()
                }

                override fun onRightMenuOpen() {
                    clearEditionStatus()
                }
            })

    override fun clearEditionStatus() {
        viewData = viewData.copy(edited = false)
        updateEditable()
    }

    override fun setUpExpanderListener() = with(itemBinding) {
        setExpanderClickListener {
            viewData = viewData.copy(expanded = !viewData.expanded)
            clickListener?.onExpanderClick?.invoke(viewData)
        }
    }

    override fun setUpAddListener() = with(itemBinding) {
        setAddClickListener {
            swipeLayoutItemTaskHeaderRoot.resetStatus()
            root.findNavController().navigateToAddTask(viewData.id)
        }
    }

    private fun NavController.navigateToAddTask(groupId: Long) =
        navigate(TasksFragmentDirections.tasksToSingleTaskAddition(groupId))

    override fun setUpEditListener() = with(itemBinding) {
        setEditClickListener {
            viewData = viewData.copy(edited = !viewData.edited)
            updateEditable()
            swipeLayoutItemTaskHeaderRoot.resetStatus()
        }
    }

    override fun setUpEditionSubmitListener() = with(itemBinding) {
        setEditionSubmitClickListener {
            if (didItemNameChange()) {
                viewData = viewData.copy(
                    name = editTextItemTaskHeaderName.text.toString()
                )
                clickListener?.onEditionSubmitClick?.invoke(viewData)
            } else {
                clearEditionStatus()
            }
        }
    }

    private fun didItemNameChange() =
        viewData.name != itemBinding.editTextItemTaskHeaderName.text.toString()

    override fun setUpRemoveListener() = with(itemBinding) {
        setRemoveClickListener {
            swipeLayoutItemTaskHeaderRoot.resetStatus()
            clickListener?.onRemoveClick?.invoke(viewData)
        }
    }
}
