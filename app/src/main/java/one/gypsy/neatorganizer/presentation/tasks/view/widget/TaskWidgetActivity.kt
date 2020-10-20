package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_task_widget.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ActivityTaskWidgetBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class TaskWidgetActivity : AppCompatActivity() {
    private val tasksViewModel: TasksWidgetViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(MANAGED_WIDGET_ID_KEY, MANAGED_WIDGET_INVALID_ID),
            intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID)
        )
    }
    private lateinit var viewBinding: ActivityTaskWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_widget)
        setSupportActionBar(manageToolbar)
        findNavController(R.id.navigationFragmentsContainer).setGraph(
            R.navigation.navigation_task_widget,
            createStartDestinationBundle()
        )
        viewBinding.apply {
            viewModel = tasksViewModel
            lifecycleOwner = this@TaskWidgetActivity
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.widget_list_manage_menu, menu)
        return true
    }

    private fun createStartDestinationBundle() = Bundle().apply {
        putLong(
            MANAGED_GROUP_ID_KEY,
            intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        findNavController().navigate(R.id.task_group_add)
//        navigateToAddTaskDialog()
        return true
    }

    private fun navigateToAddTaskDialog() {
//        val groupId = intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID)
//        if (groupId == MANAGED_GROUP_INVALID_ID) {
//            container.findNavController().navigate(
//                TaskGroupManageActivityDirections.actionTaskGroupManageActivityToAddSingleTaskDialogFragment(
//                    groupId
//                )
//            )
//        }
    }
}