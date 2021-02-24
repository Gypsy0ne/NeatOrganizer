package one.gypsy.neatorganizer.task

import one.gypsy.neatorganizer.core.widget.WidgetNotifier
import one.gypsy.neatorganizer.core.widget.WidgetRemoteViewManager
import one.gypsy.neatorganizer.task.model.TaskListMapper
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetNotifier
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetRemoteViewManager
import one.gypsy.neatorganizer.task.vm.AddTaskGroupViewModel
import one.gypsy.neatorganizer.task.vm.AddTaskViewModel
import one.gypsy.neatorganizer.task.vm.RemoveTaskGroupViewModel
import one.gypsy.neatorganizer.task.vm.TaskWidgetContentManageViewModel
import one.gypsy.neatorganizer.task.vm.TaskWidgetSelectionViewModel
import one.gypsy.neatorganizer.task.vm.TasksViewModel
import one.gypsy.neatorganizer.task.vm.TasksWidgetConfigurationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val taskModule = module {
    taskUtilsModule
    taskViewModelModule
}

private val taskUtilsModule = module {
    factory { TaskListMapper() }
    factory<WidgetRemoteViewManager>(named("taskRemoteViewManager")) {
        TaskWidgetRemoteViewManager(
            context = get(),
            widgetManager = get(),
            loadTitledTaskWidgetUseCase = get(),
            removeTaskWidgetUseCase = get()
        )
    }
    factory<WidgetNotifier>(named("taskWidgetNotifier")) { TaskWidgetNotifier(androidContext()) }
}

private val taskViewModelModule = module {
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
