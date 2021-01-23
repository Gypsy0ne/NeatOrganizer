package one.gypsy.neatorganizer.presentation.tasks.view.widget.configuration

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.WidgetTasksConfigurationBinding
import one.gypsy.neatorganizer.presentation.common.WidgetConfigurationActivity
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetCreationStatus
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetConfigurationViewModel
import one.gypsy.neatorganizer.utils.extensions.showShortToast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class TasksAppWidgetConfigureActivity : WidgetConfigurationActivity() {

    private val widgetConfigurationViewModel: TasksWidgetConfigurationViewModel by viewModel()
    override val widgetViewManager: WidgetRemoteViewManager by inject(named("taskRemoteViewManager"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBoundContentView()
        observeCreationStatus()
    }

    private fun setDataBoundContentView() =
        DataBindingUtil.setContentView<WidgetTasksConfigurationBinding>(
            this,
            R.layout.widget_tasks_configuration
        ).also {
            it.bindViews()
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
        widgetConfigurationViewModel.widgetCreationStatus.observe(this) {
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
        }
    }
}
