package one.gypsy.neatorganizer.task.view.widget

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_task_widget.manageToolbar
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_INVALID_ID
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.MANAGED_GROUP_ID_KEY
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.MANAGED_GROUP_INVALID_ID

class TaskWidgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_widget)
        setUpActionBar()
        setNavigationGraph()
        startWidgetSynchronizationService()
    }

    private fun setUpActionBar() {
        setSupportActionBar(manageToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setNavigationGraph() = findNavController(R.id.navigationFragmentsContainer)
        .setGraph(R.navigation.navigation_task_widget, createStartDestinationBundle())

    private fun createStartDestinationBundle() = Bundle().apply {
        putLong(
            MANAGED_GROUP_ID_KEY,
            intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID)
        )
        putInt(
            MANAGED_WIDGET_ID_KEY,
            intent.getIntExtra(MANAGED_WIDGET_ID_KEY, MANAGED_WIDGET_INVALID_ID)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, TaskWidgetSynchronizationService::class.java))
    }

    private fun startWidgetSynchronizationService() =
        Intent(this, TaskWidgetSynchronizationService::class.java).also { intent ->
            startService(intent)
        }
}
