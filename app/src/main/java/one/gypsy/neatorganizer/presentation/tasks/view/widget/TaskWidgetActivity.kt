package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_task_widget.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ActivityTaskWidgetBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetViewModel
import one.gypsy.neatorganizer.utils.extensions.requestEdit
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
    private val editTitleMenuItem by lazy { viewBinding.manageToolbar.menu.getItem(manageToolbar.menu.size - 1) }

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
            executePendingBindings()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.edit_group -> {
            onEditGroupTitleClicked()
            true
        }
        else -> false
    }

    //try to move views logic to fragment
    //changing icons from observer doesnt work
    private fun onEditGroupTitleClicked() = with(tasksViewModel) {
        if (titleEdited.value == true) {
            onTitleEditionFinished(viewBinding.groupTitle.text.toString())
            editTitleMenuItem.setIcon(R.drawable.ic_edit_white_24)
        } else {
            onTitleEditionStarted()
            editTitleMenuItem.setIcon(R.drawable.ic_check_dark_yellow_24)
            //for some reason data bound field in view doesnt change its value on livedata update
            //manual changing works
            groupTitle.requestEdit()
        }
    }
}