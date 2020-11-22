package one.gypsy.neatorganizer.presentation.tasks.view.widget.configuration

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.WidgetTasksConfigurationBinding
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetCreationStatus
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetConfigurationViewModel
import one.gypsy.neatorganizer.utils.extensions.showShortToast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TasksAppWidgetConfigureActivity : AppCompatActivity() {

    private val widgetConfigurationViewModel: TasksWidgetConfigurationViewModel by viewModel()
    private val widgetViewManager: WidgetRemoteViewManager by inject()
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        findWidgetIdFromIntent()
        setActivityResult(RESULT_CANCELED)
        invalidateIntentWithWidgetId()
        setDataBoundContentView()
        observeCreationStatus()
    }

    private fun AppCompatActivity.setActivityResult(status: Int) {
        val result = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        setResult(status, result)
    }

    private fun invalidateIntentWithWidgetId() {
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }
    }

    private fun findWidgetIdFromIntent() = intent.extras?.let {
        appWidgetId = it.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
        )
    }

    private fun setDataBoundContentView() = with(
        DataBindingUtil.setContentView<WidgetTasksConfigurationBinding>(
            this,
            R.layout.widget_tasks_configuration
        )
    ) {
        bindViews()
        setContentView(root)
    }

    private fun WidgetTasksConfigurationBinding.bindViews() {
        configurationViewModel = widgetConfigurationViewModel
        lifecycleOwner = this@TasksAppWidgetConfigureActivity
        bindButtons()
        bindRecyclerView()
        executePendingBindings()
    }

    private fun WidgetTasksConfigurationBinding.bindButtons() {
        cancelConfiguration.setOnClickListener {
            finish()
        }
        submitConfiguration.setOnClickListener {
            widgetConfigurationViewModel.onSubmitClicked(appWidgetId)
        }
    }

    private fun WidgetTasksConfigurationBinding.bindRecyclerView() {
        tasksAdapter = TaskGroupEntriesAdapter(widgetConfigurationViewModel.selectedTaskGroup) {
            widgetConfigurationViewModel.onTaskGroupSelected(it)
        }
        layoutManager = LinearLayoutManager(baseContext)
    }

    private fun observeCreationStatus() {
        widgetConfigurationViewModel.widgetCreationStatus.observe(this, Observer {
            when (it) {
                TaskWidgetCreationStatus.TaskNotSelectedStatus -> {
                    baseContext.showShortToast(resources.getString(R.string.task_widget_creation_task_warning))
                }
                TaskWidgetCreationStatus.ColorNotPickedStatus -> {
                    baseContext.showShortToast((resources.getString(R.string.task_widget_creation_color_warning)))
                }
                TaskWidgetCreationStatus.CreationSuccessStatus -> {
                    onWidgetCreationFinish()
                }
            }
        })
    }

    private fun onWidgetCreationFinish() {
        widgetViewManager.updateWidget(appWidgetId)
        setActivityResult(RESULT_OK)
        finish()
    }

}
