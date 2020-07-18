package one.gypsy.neatorganizer.di

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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}