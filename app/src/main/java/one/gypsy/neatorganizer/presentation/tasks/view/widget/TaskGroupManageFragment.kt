package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentTaskGroupManageBinding
import one.gypsy.neatorganizer.presentation.tasks.view.GroupedTasksAdapter
import one.gypsy.neatorganizer.presentation.tasks.view.TaskSubItemClickListener
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetContentManageViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TaskGroupManageFragment : Fragment() {

    private val tasksViewModel: TaskWidgetContentManageViewModel by viewModel {
        parametersOf(arguments?.getLong(MANAGED_GROUP_ID_KEY) ?: MANAGED_GROUP_INVALID_ID)
    }
    private lateinit var viewBinding: FragmentTaskGroupManageBinding
    private val subItemClickListener = TaskSubItemClickListener(
        onDoneClick = { tasksViewModel.onTaskUpdate(it) },
        onEditionSubmitClick = { tasksViewModel.onTaskUpdate(it) },
        onRemoveClick = { tasksViewModel.onRemove(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_task_group_manage,
            container,
            false
        )
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setUpContentBinding()
        observeNewGroupSelectionResult()
    }

    private fun FragmentTaskGroupManageBinding.setUpContentBinding() {
        viewModel = tasksViewModel
        lifecycleOwner = this@TaskGroupManageFragment
        setUpRecyclerView()
    }

    private fun FragmentTaskGroupManageBinding.setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = GroupedTasksAdapter(subItemClickListener = subItemClickListener)
        tasks.itemAnimator = null
        executePendingBindings()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.add_entry -> {
            navigateToAddTaskDialog()
            true
        }
        else -> false
    }

    private fun navigateToAddTaskDialog() {
        val groupId = arguments?.getLong(MANAGED_GROUP_ID_KEY) ?: MANAGED_GROUP_INVALID_ID
        if (groupId != MANAGED_GROUP_INVALID_ID) {
            val direction = TaskGroupManageFragmentDirections
                .actionTaskGroupManageFragmentToAddSingleTaskDialog(groupId)
            findNavController(this).navigate(direction)
        }
    }

    private fun observeNewGroupSelectionResult() =
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Long?>(SELECTED_WIDGET_GROUP_ID_KEY)
            ?.observe(viewLifecycleOwner) {
                onNewTaskGroupSelected(it)
            }

    private fun onNewTaskGroupSelected(selectedGroupId: Long?) {
        if (selectedGroupId != null) {
            tasksViewModel.loadTasksData(selectedGroupId)
        }
    }
}
