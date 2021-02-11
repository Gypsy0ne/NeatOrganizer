package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.presentation.common.WidgetNotifier
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetNotifier
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.RemoveTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetContentManageViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetSelectionViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksWidgetConfigurationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tasksUtilsModule = module {
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

val tasksViewModelModule = module {
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
