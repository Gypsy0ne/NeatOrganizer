package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_task_widget.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ActivityTaskWidgetBinding
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetKeyring.MANAGED_GROUP_ID_KEY
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetKeyring.MANAGED_GROUP_INVALID_ID
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetKeyring.SELECTED_WIDGET_GROUP_ID_KEY
import one.gypsy.neatorganizer.presentation.tasks.view.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import one.gypsy.neatorganizer.presentation.tasks.view.widget.WidgetKeyring.MANAGED_WIDGET_INVALID_ID
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetDataLoadingStatus
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

// TODO whole app bar logic might be moved to manage fragment
class TaskWidgetActivity : AppCompatActivity() {

    private val tasksViewModel: TasksWidgetViewModel by viewModel {
        parametersOf(intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID))
    }
    private lateinit var viewBinding: ActivityTaskWidgetBinding
    private lateinit var appBarMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setSupportActionBar(manageToolbar)
        setNavigationGraph()
        startWidgetSynchronizationService()
        observeDataLoadingStatus()
        findNavController(R.id.navigationFragmentsContainer).observeNewGroupSelectionResult()
    }

    private fun setUpBinding() {
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_widget)
        viewBinding.apply {
            viewModel = tasksViewModel
            lifecycleOwner = this@TaskWidgetActivity
            executePendingBindings()
        }
    }

    private fun setNavigationGraph() = findNavController(R.id.navigationFragmentsContainer)
        .setGraph(R.navigation.navigation_task_widget, createStartDestinationBundle())

    private fun createStartDestinationBundle() = Bundle().apply {
        putLong(
            MANAGED_GROUP_ID_KEY,
            intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID)
        )
    }

    private fun observeDataLoadingStatus() = tasksViewModel.widgetDataLoaded.observe(this) {
        if (it == TaskWidgetDataLoadingStatus.LoadingError) {
            findNavController(R.id.navigationFragmentsContainer).navigateToSelectTaskGroupDialog()
        }
    }

    private fun NavController.navigateToSelectTaskGroupDialog() {
        val widgetId = intent.getIntExtra(MANAGED_WIDGET_ID_KEY, MANAGED_WIDGET_INVALID_ID)
        if (widgetId != MANAGED_WIDGET_INVALID_ID) {
            navigate(
                TaskGroupManageFragmentDirections
                    .widgetTaskGroupManageToTaskGroupSelection(widgetId)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, TaskWidgetSynchronizationService::class.java))
    }

    private fun startWidgetSynchronizationService() =
        Intent(this, TaskWidgetSynchronizationService::class.java).also { intent ->
            startService(intent)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.widget_list_manage_menu, menu)
        appBarMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit_group_title -> {
                onEditGroupTitleClicked()
                true
            }
            R.id.save_group_title -> {
                onSaveGroupTitleClicked()
                true
            }
            else -> false
        }
    }

    private fun onEditGroupTitleClicked() {
        appBarMenu.findItem(R.id.edit_group_title).isVisible = false
        appBarMenu.findItem(R.id.save_group_title).isVisible = true
        tasksViewModel.onTitleEditionStarted()
    }

    private fun onSaveGroupTitleClicked() {
        appBarMenu.findItem(R.id.edit_group_title).isVisible = true
        appBarMenu.findItem(R.id.save_group_title).isVisible = false
        tasksViewModel.onTitleEditionFinished(viewBinding.barTitle.text.toString())
    }

    private fun NavController.observeNewGroupSelectionResult() =
        currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Long?>(SELECTED_WIDGET_GROUP_ID_KEY)
            ?.observe(this@TaskWidgetActivity) {
                onNewTaskSelected(it)
            }

    private fun onNewTaskSelected(selectedGroupId: Long?) = if (selectedGroupId != null) {
        tasksViewModel.loadTaskGroupData(selectedGroupId)
    } else {
        finish()
    }
}
