package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.TaskWidgetDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserTaskWidgetDataSource
import one.gypsy.neatorganizer.domain.interactors.tasks.AddSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.AddTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.CreateTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.DeleteTaskWidgetById
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroupEntries
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroups
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupId
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupIdObservable
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllTaskWidgetIds
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllTaskWidgets
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.GetSingleTaskGroupWithTasksById
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTaskGroupIdByWidgetId
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTitledTaskWidgetByIdObservable
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTitledTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.RemoveTaskGroupById
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTask
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroup
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateSingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.interactors.tasks.UpdateTaskWidgetLinkedGroup
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
import one.gypsy.neatorganizer.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.repositories.tasks.TaskWidgetsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tasksDataSourceModule = module {
    factory<SingleTasksDataSource> { UserSingleTasksDataSource(get()) }
    factory<SingleTaskGroupsDataSource> { UserSingleTaskGroupsDataSource(get()) }
    factory<TaskWidgetDataSource> { UserTaskWidgetDataSource(get()) }
}

val tasksRepositoryModule = module {
    factory { SingleTasksRepository(get()) }
    factory { SingleTaskGroupsRepository(get()) }
    factory { TaskWidgetsRepository(get()) }
}

val tasksUseCaseModule = module {
    factory { AddSingleTask(get()) }
    factory { AddTaskGroup(get()) }
    factory { GetAllSingleTaskGroups(get()) }
    factory { RemoveSingleTask(get()) }
    factory { RemoveTaskGroup(get()) }
    factory { RemoveTaskGroupById(get()) }
    factory { UpdateSingleTask(get()) }
    factory { UpdateSingleTaskGroupWithTasks(get()) }
    factory { GetAllSingleTaskGroupEntries(get()) }
    factory { CreateTaskWidget(get()) }
    factory { LoadTitledTaskWidget(get()) }
    factory { GetAllSingleTasksByGroupId(get()) }
    factory { GetSingleTaskGroupWithTasksById(get()) }
    factory { GetSingleTaskGroupById(get()) }
    factory { GetAllSingleTasksByGroupIdObservable(get()) }
    factory { UpdateSingleTaskGroup(get()) }
    factory { GetAllTaskWidgetIds(get()) }
    factory { DeleteTaskWidgetById(get()) }
    factory { UpdateTaskWidgetLinkedGroup(get()) }
    factory { GetAllTaskWidgets(get()) }
    factory { GetTitledTaskWidgetByIdObservable(get()) }
    factory { GetTaskGroupIdByWidgetId(get()) }
}

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
