package one.gypsy.neatorganizer.di

import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTasksDataSource
import one.gypsy.neatorganizer.domain.interactors.tasks.*
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListMapper
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.RemoveTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tasksDataSourceModule = module {
    factory<SingleTasksDataSource> { UserSingleTasksDataSource(get()) }
    factory<SingleTaskGroupsDataSource> { UserSingleTaskGroupsDataSource(get()) }
}

val tasksRepositoryModule = module {
    factory { SingleTasksRepository(get()) }
    factory { SingleTaskGroupsRepository(get()) }
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
}

val tasksUtilsModule = module {
    factory { TaskListMapper() }
}

val tasksViewModelModule = module {
    viewModel {
        TasksViewModel(
            removeSingleSingleTaskUseCase = get(),
            getAllSingleTaskGroupsUseCase = get(),
            taskListMapper = get(),
            updateSingleTaskGroupUseCase = get(),
            updateSingleTaskUseCase = get()
        )
    }
    viewModel { RemoveTaskGroupViewModel(get()) }
    viewModel { (id: Long) -> AddTaskViewModel(get(), id) }
    viewModel { AddTaskGroupViewModel(get()) }
}