package one.gypsy.neatorganizer.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.core.SectionFragment
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.databinding.FragmentTasksBinding
import one.gypsy.neatorganizer.task.vm.TasksViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TasksFragment : SectionFragment() {

    private val tasksViewModel: TasksViewModel by viewModel()
    private lateinit var fragmentBinding: FragmentTasksBinding

    private val headerClickListener = TaskHeaderClickListener(
        onExpanderClick = { tasksViewModel.onExpand(it) },
        onEditionSubmitClick = { tasksViewModel.onHeaderUpdate(it) },
        onRemoveClick = {
            findNavController().showRemoveConfirmationDialog(
                it.id,
                it.subItemsCount
            )
        }
    )

    private val subItemClickListener = TaskSubItemClickListener(
        onDoneClick = { tasksViewModel.onTaskUpdate(it) },
        onEditionSubmitClick = { tasksViewModel.onTaskUpdate(it) },
        onRemoveClick = { tasksViewModel.onRemove(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        findNavController().navigate(R.id.taskGroupAddition)
        return true
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = GroupedTasksAdapter(headerClickListener, subItemClickListener)
        recyclerViewFragmentTasks.itemAnimator = null
        executePendingBindings()
    }

    private fun NavController.showRemoveConfirmationDialog(
        taskGroupId: Long,
        subItemsCount: Int
    ) = navigate(
        TasksFragmentDirections.tasksToSingleTaskGroupRemoveConfirmation(
            taskGroupId,
            subItemsCount
        )
    )
}
