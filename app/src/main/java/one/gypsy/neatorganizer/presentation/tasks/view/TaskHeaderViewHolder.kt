package one.gypsy.neatorganizer.presentation.tasks.view


import android.view.View
import androidx.navigation.findNavController
import one.gypsy.neatorganizer.databinding.ItemTaskHeaderBinding
import one.gypsy.neatorganizer.generated.callback.OnClickListener
import one.gypsy.neatorganizer.presentation.people.view.PeopleFragmentDirections
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskHeaderViewHolder(
    val itemBinding: ItemTaskHeaderBinding,
    val itemClickListener: ClickListener
) : TaskViewHolder(itemBinding.root) {

    interface ClickListener {
        fun onExpanderClick(taskItem: TaskListItem.TaskListHeader)
    }


    override fun bind(data: TaskListItem) {
        itemBinding.apply {
            this.headerItem = data as TaskListItem.TaskListHeader
            this.expanderClickListener = View.OnClickListener {
                data.expanded = !data.expanded
                itemClickListener.onExpanderClick(data) }
            this.addClickListener = View.OnClickListener {
                navigateToAddTask(data.groupId, this.root)
            }
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



}