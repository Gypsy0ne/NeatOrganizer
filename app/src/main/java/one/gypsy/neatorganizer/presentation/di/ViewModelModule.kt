package one.gypsy.neatorganizer.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import one.gypsy.neatorganizer.presentation.people.vm.AddPersonViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskGroupViewModel
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskViewModel
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

//    @Binds
//    @IntoMap
//    @ViewModelKey(RateInteractionViewModel::class)
//    abstract fun bindRateInteractionViewModelViewModel(viewModel: RateInteractionViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(PersonEntryViewModel::class)
//    abstract fun bindPersonEntryViewModel(viewModel: PersonEntryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}