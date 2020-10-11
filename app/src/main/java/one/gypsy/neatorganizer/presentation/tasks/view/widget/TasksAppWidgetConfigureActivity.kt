package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.WidgetTasksConfigurationBinding
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetCreationStatus
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetConfigurationViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TasksAppWidgetConfigureActivity : AppCompatActivity() {
    private val widgetConfigurationViewModel: TasksWidgetConfigurationViewModel by viewModel()
    private val widgetViewManager: WidgetRemoteViewManager by inject()
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)
        findWidgetIdFromIntent()
        // If this activity was started with an intent without an app widget ID, finish with an error.
        invalidateIntentWithWidgetId()
        setDataBoundContentView()
        observeCreationStatus()
    }

    private fun invalidateIntentWithWidgetId() {
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }
    }

    private fun findWidgetIdFromIntent() {
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }
    }

    private fun setDataBoundContentView() = with(
        DataBindingUtil.setContentView<WidgetTasksConfigurationBinding>(
            this,
            R.layout.widget_tasks_configuration
        )
    ) {
        bindViewFields()
        setContentView(root)
    }

    private fun WidgetTasksConfigurationBinding.bindViewFields() {
        configurationViewModel = widgetConfigurationViewModel
        tasksAdapter = TaskGroupEntriesAdapter(widgetConfigurationViewModel.selectedTaskGroup) {
            widgetConfigurationViewModel.onTaskGroupSelected(it)
        }
        cancelButtonListener = View.OnClickListener {
            finish()
        }
        submitButtonListener = View.OnClickListener {
            widgetConfigurationViewModel.onSubmitClicked(appWidgetId)
        }
        layoutManager = LinearLayoutManager(baseContext)
        lifecycleOwner = this@TasksAppWidgetConfigureActivity
        executePendingBindings()
    }

    private fun observeCreationStatus() {
        widgetConfigurationViewModel.widgetCreationStatus.observe(this, Observer {
            when (it) {
                TaskWidgetCreationStatus.TaskNotSelectedStatus -> {
                    showStatusToast(resources.getString(R.string.task_widget_creation_task_warning))
                }
                TaskWidgetCreationStatus.ColorNotPickedStatus -> {
                    showStatusToast(resources.getString(R.string.task_widget_creation_color_warning))
                }
                TaskWidgetCreationStatus.CreationSuccessStatus -> {
                    onWidgetCreationFinish()
                }
            }
        })
    }

    private fun onWidgetCreationFinish() {
        val appWidgetManager = AppWidgetManager.getInstance(baseContext)
        widgetViewManager.updateWidget(baseContext, appWidgetManager, appWidgetId)

        val resultValue = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        setResult(RESULT_OK, resultValue)
        finish()
    }

    private fun showStatusToast(statusText: String) =
        Toast.makeText(baseContext, statusText, Toast.LENGTH_SHORT).show()
}


// Write the prefix to the SharedPreferences object for this widget

// Read the prefix from the SharedPreferences object for this widget.
// If there is no preference saved, get the default from a resource
internal fun loadTitlePref(context: Context, appWidgetId: Int): String {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
//    val titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null)
//    return titleValue ?: context.getString(R.string.appwidget_text)
    return context.getString(R.string.appwidget_text)
}

internal fun deleteTitlePref(context: Context, appWidgetId: Int) {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
//    prefs.remove(PREF_PREFIX_KEY + appWidgetId)
//    prefs.apply()
}