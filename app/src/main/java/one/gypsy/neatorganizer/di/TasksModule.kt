package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.data.repositories.tasks.TaskWidgetPreferencesRepository
import one.gypsy.neatorganizer.domain.datasource.tasks.*
import one.gypsy.neatorganizer.domain.interactors.tasks.*
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetRemoteViewManager
import one.gypsy.neatorganizer.presentation.tasks.vm.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tasksDataSourceModule = module {
    factory<SingleTasksDataSource> { UserSingleTasksDataSource(get()) }
    factory<SingleTaskGroupsDataSource> { UserSingleTaskGroupsDataSource(get()) }
    factory<TaskWidgetDataSource> { UserTaskWidgetDataSource(get()) }
}

val tasksRepositoryModule = module {
    factory { SingleTasksRepository(get()) }
    factory { SingleTaskGroupsRepository(get()) }
    factory { TaskWidgetPreferencesRepository(get()) }
}

val tasksUseCaseModule = module {
    factory { AddSingleTask(get()) }
    factory { AddTaskGroup(get()) }
    factory { GetAllSingleTaskGroups(get()) }
    factory { RemoveSingleTask(get()) }
    factory { RemoveTaskGroup(get()) }
    factory { RemoveTaskGroupById(get()) }
    factory { UpdateSingleTask(get()) }
    factory { UpdateTaskGroup(get()) }
    factory { GetAllSingleTaskGroupEntries(get()) }
    factory { SaveTaskWidget(get()) }
    factory { LoadTaskWidget(get()) }
    factory { GetAllSingleTasksByGroupId(get()) }
    factory { GetSingleTaskGroupWithTasksById(get()) }
    factory { GetSingleTaskGroupById(get()) }
    factory { GetAllSingleTasksByGroupIdObservable(get()) }
}

val tasksUtilsModule = module {
    factory { TaskListMapper() }
    factory<WidgetRemoteViewManager> { TaskWidgetRemoteViewManager(get(), get(), get()) }
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
    viewModel { (id: Long) -> TaskWidgetContentManageViewModel(id, get(), get(), get()) }
    viewModel { RemoveTaskGroupViewModel(get()) }
    viewModel { (id: Long) -> AddTaskViewModel(get(), id) }
    viewModel { AddTaskGroupViewModel(get()) }
    viewModel { TasksWidgetConfigurationViewModel(get(), get()) }
    viewModel { (widgetId: Int, taskGroupId: Long) ->
        TasksWidgetViewModel(
            taskGroupId = taskGroupId,
            widgetId = widgetId,
            get()
        )
    }
}