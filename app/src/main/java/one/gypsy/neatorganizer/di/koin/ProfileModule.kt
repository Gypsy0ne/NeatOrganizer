package one.gypsy.neatorganizer.di.koin

import one.gypsy.neatorganizer.domain.interactors.profile.GetPersonHistory
import one.gypsy.neatorganizer.domain.interactors.profile.GetPersonProfile
import one.gypsy.neatorganizer.presentation.profile.vm.InteractionEntryViewModel
import one.gypsy.neatorganizer.presentation.profile.vm.PersonProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val profileUseCaseModule = module {
    factory {
        GetPersonHistory(
            get()
        )
    }
    factory {
        GetPersonProfile(
            get()
        )
    }
}

val profileViewModelModule = module {
    viewModel { InteractionEntryViewModel() }
    viewModel { (id: Long) -> PersonProfileViewModel(get(), id) }
}