package one.gypsy.neatorganizer.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import one.gypsy.neatorganizer.presentation.people.vm.AddPersonViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RemoveRoutineViewModel
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.RemoveTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel::class)
    abstract fun bindPeopleViewModel(viewModel: PeopleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddPersonViewModel::class)
    abstract fun bindAddPersonViewModel(viewModel: AddPersonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(viewModel: TasksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddTaskGroupViewModel::class)
    abstract fun bindAddTaskGroupViewModel(viewModel: AddTaskGroupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoutinesViewModel::class)
    abstract fun bindRoutinesViewModel(viewModel: RoutinesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddRoutineViewModel::class)
    abstract fun bindAddRoutineViewModel(viewModel: AddRoutineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RemoveRoutineViewModel::class)
    abstract fun bindRemoveRoutineViewModel(viewModel: RemoveRoutineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RemoveTaskGroupViewModel::class)
    abstract fun bindRemoveTaskGroupViewModel(viewModel: RemoveTaskGroupViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}