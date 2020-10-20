package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentTaskGroupManageBinding
import one.gypsy.neatorganizer.presentation.tasks.view.GroupedTasksAdapter
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetContentManageViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TaskGroupManageFragment : Fragment() {
    private val tasksViewModel: TaskWidgetContentManageViewModel by viewModel {
        parametersOf(arguments?.getLong(MANAGED_GROUP_ID_KEY) ?: MANAGED_GROUP_INVALID_ID)
    }
    private lateinit var viewBinding: FragmentTaskGroupManageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_task_group_manage, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            viewModel = tasksViewModel
            lifecycleOwner = this@TaskGroupManageFragment
        }
        setUpRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        findNavController().navigate(R.id.task_group_add)
//        navigateToAddTaskDialog()
        return true
    }

    //    private fun navigateToAddTaskDialog() {
//        val groupId = intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID)
//        if(groupId == MANAGED_GROUP_INVALID_ID) {
//            container.findNavController().navigate(
//                TaskGroupManageActivityDirections.actionTaskGroupManageActivityToAddSingleTaskDialogFragment(
//                    groupId
//                )
//            )
//        }
//    }
//
    private fun setUpRecyclerView() = viewBinding.apply {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = GroupedTasksAdapter()
        tasks.itemAnimator = null
        executePendingBindings()
    }

}
