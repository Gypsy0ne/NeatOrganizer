package one.gypsy.neatorganizer.task

import android.appwidget.AppWidgetManager
import one.gypsy.neatorganizer.core.widget.WidgetNotifier
import one.gypsy.neatorganizer.core.widget.WidgetRemoteViewManager
import one.gypsy.neatorganizer.task.model.TaskListMapper
import one.gypsy.neatorganizer.task.view.widget.configuration.TaskWidgetConfigureActivity.Companion.TASK_REMOTE_VIEW_MANAGER
import one.gypsy.neatorganizer.task.view.widget.management.TaskWidgetNotifier
import one.gypsy.neatorganizer.task.view.widget.remote.TaskWidgetRemoteViewManager
import one.gypsy.neatorganizer.task.vm.*
import one.gypsy.tutorial.FeatureShowcase
import one.gypsy.tutorial.SwipeShowcase
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val taskUtilsModule = module {
    factory { TaskListMapper() }
    factory<WidgetRemoteViewManager>(named(TASK_REMOTE_VIEW_MANAGER)) {
        TaskWidgetRemoteViewManager(
            context = get(),
            widgetManager = AppWidgetManager.getInstance(get()),
            loadTitledTaskWidgetUseCase = get(),
            removeTaskWidgetUseCase = get()
        )
    }
    factory<WidgetNotifier>(named("taskWidgetNotifier")) { TaskWidgetNotifier(androidContext()) }
}

val tasksUiModule = module {
    single<FeatureShowcase> { SwipeShowcase() }
}

val taskViewModelModule = module {
    viewModel {
        TasksViewModel(
            removeSingleTaskUseCase = get(),
            getAllSingleTaskGroupsUseCase = get(),
            taskListMapper = get(),
            updateSingleTaskGroupUseCase = get(),
            updateSingleTaskUseCase = get()
        )
    }
    viewModel {
        TaskWidgetSelectionViewModel(
            getAllTaskGroupEntriesUseCase = get(),
            widgetUpdateUseCase = get()
        )
    }
    viewModel { (id: Long) ->
        TaskWidgetContentManageViewModel(
            taskGroupId = id,
            getSingleTaskGroupWithTasksUseCase = get(),
            updateSingleTaskUseCase = get(),
            removeSingleTaskUseCase = get(),
            updateTaskGroupUseCase = get()
        )
    }
    viewModel { RemoveTaskGroupViewModel(removeTaskGroupByIdUseCase = get()) }
    viewModel { (id: Long) -> AddTaskViewModel(addSingleTaskUseCase = get(), groupId = id) }
    viewModel { AddTaskGroupViewModel(addTaskGroupUseCase = get()) }
    viewModel {
        TasksWidgetConfigurationViewModel(
            getAllTaskGroupEntriesUseCase = get(),
            widgetCreationUseCase = get()
        )
    }
}
