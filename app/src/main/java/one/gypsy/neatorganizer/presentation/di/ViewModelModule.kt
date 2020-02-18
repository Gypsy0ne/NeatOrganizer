package one.gypsy.neatorganizer.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import one.gypsy.neatorganizer.presentation.people.vm.AddPersonViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PersonEntryViewModel

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

//    @Binds
//    @IntoMap
//    @ViewModelKey(PersonEntryViewModel::class)
//    abstract fun bindPersonEntryViewModel(viewModel: PersonEntryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}