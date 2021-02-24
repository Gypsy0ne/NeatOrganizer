package one.gypsy.neatorganizer.task.view.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.autofit.et.lib.AutoFitEditText
import one.gypsy.neatorganizer.core.binding.setEditionEnabled
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_INVALID_ID
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.databinding.FragmentTaskGroupManageBinding
import one.gypsy.neatorganizer.task.view.GroupedTasksAdapter
import one.gypsy.neatorganizer.task.view.TaskSubItemClickListener
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.MANAGED_GROUP_ID_KEY
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.MANAGED_GROUP_INVALID_ID
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.SELECTED_WIDGET_GROUP_ID_KEY
import one.gypsy.neatorganizer.task.vm.TaskWidgetContentManageViewModel
import one.gypsy.neatorganizer.task.vm.TaskWidgetDataLoadingStatus
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class TaskGroupManageFragment : Fragment() {

    private val tasksViewModel: TaskWidgetContentManageViewModel by viewModel {
        parametersOf(arguments?.getLong(MANAGED_GROUP_ID_KEY) ?: MANAGED_GROUP_INVALID_ID)
    }
    private lateinit var viewBinding: FragmentTaskGroupManageBinding
    private val subItemClickListener = TaskSubItemClickListener(
        onDoneClick = { tasksViewModel.onTaskUpdate(it) },
        onEditionSubmitClick = { tasksViewModel.onTaskUpdate(it) },
        onRemoveClick = { tasksViewModel.onRemove(it) }
    )
    private lateinit var appBarMenu: Menu
    private val titleView by lazy {
        (activity as? AppCompatActivity)?.supportActionBar
            ?.customView
            ?.findViewById<AutoFitEditText>(R.id.barTitle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        findNavController().observeNewGroupSelectionResult()
        observeDataLoadingStatus()
        setTitleBarCustomView()
        initTitleViewBehavior()
    }

    private fun FragmentTaskGroupManageBinding.setUpContentBinding() {
        viewModel = tasksViewModel
        lifecycleOwner = this@TaskGroupManageFragment
        setUpRecyclerView()
        setUpTitleBar()
    }

    private fun setTitleBarCustomView() =
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.editable_title_bar)
        }

    private fun initTitleViewBehavior() =
        titleView?.also { titleView ->
            tasksViewModel.titleEdited.observe(viewLifecycleOwner) { edited ->
                setEditionEnabled(titleView, edited, true)
            }
            tasksViewModel.taskGroup.observe(viewLifecycleOwner) { taskGroup ->
                titleView.setText(taskGroup.name)
            }
        }

    private fun FragmentTaskGroupManageBinding.setUpTitleBar() {
        viewModel?.taskGroup?.observe(viewLifecycleOwner) {
            titleView?.setText(it.name)
        }
    }

    private fun FragmentTaskGroupManageBinding.setUpRecyclerView() {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = GroupedTasksAdapter(subItemClickListener = subItemClickListener)
        tasks.itemAnimator = null
        executePendingBindings()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.widget_list_manage_menu, menu)
        appBarMenu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_group_title -> onEditGroupTitleClicked()
            R.id.save_group_title -> onSaveGroupTitleClicked()
            R.id.add_entry -> findNavController(this).navigateToAddTaskDialog()
        }
        return true
    }

    private fun onEditGroupTitleClicked() {
        appBarMenu.findItem(R.id.edit_group_title).isVisible = false
        appBarMenu.findItem(R.id.save_group_title).isVisible = true
        tasksViewModel.onEditIconClicked()
    }

    private fun onSaveGroupTitleClicked() {
        appBarMenu.findItem(R.id.edit_group_title).isVisible = true
        appBarMenu.findItem(R.id.save_group_title).isVisible = false
        titleView?.let {
            tasksViewModel.onTitleEditionFinished(
                it.text.toString(),
            )
        }
        tasksViewModel.onEditIconClicked()
    }

    private fun NavController.navigateToAddTaskDialog() =
        tasksViewModel.taskGroup.value?.id?.let {
            navigate(
                TaskGroupManageFragmentDirections
                    .widgetTaskGroupManageToSingleTaskAddition(it)
            )
        }

    private fun NavController.observeNewGroupSelectionResult() =
        currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Long?>(SELECTED_WIDGET_GROUP_ID_KEY)
            ?.observe(viewLifecycleOwner) {
                onNewTaskGroupSelected(it)
            }

    private fun onNewTaskGroupSelected(selectedGroupId: Long?) = selectedGroupId?.let {
        tasksViewModel.loadTaskGroupWithTasks(it)
    }

    private fun observeDataLoadingStatus() =
        tasksViewModel.widgetDataLoaded.observe(viewLifecycleOwner) {
            if (it == TaskWidgetDataLoadingStatus.LoadingError) {
                findNavController().navigateToSelectTaskGroupDialog()
            }
        }

    private fun NavController.navigateToSelectTaskGroupDialog() {
        val widgetId = arguments?.getInt(MANAGED_WIDGET_ID_KEY)
            ?: MANAGED_WIDGET_INVALID_ID
        if (widgetId != MANAGED_WIDGET_INVALID_ID) {
            navigate(
                TaskGroupManageFragmentDirections
                    .widgetTaskGroupManageToTaskGroupSelection(widgetId)
            )
        }
    }
}
