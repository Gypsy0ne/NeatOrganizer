package one.gypsy.neatorganizer.di.tasks

import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.RemoveTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tasksViewModelModule = module {
    viewModel {
        TasksViewModel(
            removeSingleSingleTaskUseCase = get(),
            getAllSingleTaskGroupsUseCase = get(),
            removeSingleTaskGroupUseCase = get(),
            taskListMapper = get(),
            updateSingleTaskGroupUseCase = get(),
            updateSingleTaskUseCase = get()
        )
    }
    viewModel { RemoveTaskGroupViewModel(removeTaskGroupByIdUseCase = get()) }
    viewModel { (id: Long) -> AddTaskViewModel(groupId = id, addSingleTaskUseCase = get()) }
    viewModel { AddTaskGroupViewModel(addTaskGroupUseCase = get()) }
}