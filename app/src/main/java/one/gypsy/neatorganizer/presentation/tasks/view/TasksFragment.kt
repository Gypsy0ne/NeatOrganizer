package one.gypsy.neatorganizer.presentation.tasks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentTasksBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TasksFragment : SectionFragment() {

    private val tasksViewModel: TasksViewModel by viewModel()
    private lateinit var fragmentBinding: FragmentTasksBinding
    private val onExpanderClick: (headerItem: TaskListItem.TaskListHeader) -> Unit =
        { tasksViewModel.onExpand(it) }
    private val onHeaderEditionSubmitClick: (headerItem: TaskListItem.TaskListHeader) -> Unit =
        { tasksViewModel.onHeaderUpdate(it) }
    private val onHeaderRemoveClick: (headerItem: TaskListItem.TaskListHeader) -> Unit =
        { showRemoveConfirmationDialog(it.id, it.subItemsCount) }
    private val onDoneClick: (subItem: TaskListItem.TaskListSubItem) -> Unit =
        { tasksViewModel.onTaskUpdate(it) }
    private val onTaskEditionSubmitClick: (subItem: TaskListItem.TaskListSubItem) -> Unit =
        { tasksViewModel.onTaskUpdate(it) }
    private val onTaskRemoveClick: (subItem: TaskListItem.TaskListSubItem) -> Unit =
        { tasksViewModel.onRemove(it) }


    private val headerClickListener by lazy {
        object : HeaderClickListener<TaskListItem.TaskListHeader> {
            override fun onExpanderClick(headerItem: TaskListItem.TaskListHeader) {
                tasksViewModel.onExpand(headerItem)
            }

            override fun onEditionSubmitClick(headerItem: TaskListItem.TaskListHeader) {
                tasksViewModel.onHeaderUpdate(headerItem)
            }

            override fun onRemoveClick(headerItem: TaskListItem.TaskListHeader) {
                showRemoveConfirmationDialog(headerItem.id, headerItem.subItemsCount)
            }
        }
    }

    private val subItemClickListener by lazy {
        object : SubItemClickListener<TaskListItem.TaskListSubItem> {
            override fun onDoneClick(subItem: TaskListItem.TaskListSubItem) {
                tasksViewModel.onTaskUpdate(subItem)
            }

            override fun onEditionSubmitClick(subItem: TaskListItem.TaskListSubItem) {
                tasksViewModel.onTaskUpdate(subItem)
            }

            override fun onRemoveClick(subItem: TaskListItem.TaskListSubItem) {
                tasksViewModel.onRemove(subItem)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.apply {
            viewModel = tasksViewModel
            lifecycleOwner = this@TasksFragment
        }
        setUpRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.task_group_add)
        return true
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = GroupedTasksAdapter(headerClickListener, subItemClickListener)
        recyclerViewFragmentTasks.itemAnimator = null
        executePendingBindings()
    }

    private fun showRemoveConfirmationDialog(
        taskGroupId: Long,
        subItemsCount: Int
    ) {
        with(
            TasksFragmentDirections.actionTasksToRemoveTaskGroupSubmitDialogFragment(
                taskGroupId,
                subItemsCount
            )
        ) {
            findNavController().navigate(this)
        }
    }
}