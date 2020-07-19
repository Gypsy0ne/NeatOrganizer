package one.gypsy.neatorganizer.di.koin

import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.domain.datasource.people.*
import one.gypsy.neatorganizer.domain.interactors.people.AddInteractionEntry
import one.gypsy.neatorganizer.domain.interactors.people.AddPerson
import one.gypsy.neatorganizer.domain.interactors.people.GetAllPeople
import one.gypsy.neatorganizer.domain.interactors.people.GetImageBitmap
import one.gypsy.neatorganizer.domain.interactors.profile.GetPersonHistory
import one.gypsy.neatorganizer.domain.interactors.profile.GetPersonProfile
import one.gypsy.neatorganizer.presentation.people.vm.AddPersonViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import one.gypsy.neatorganizer.presentation.people.vm.PersonEntryViewModel
import one.gypsy.neatorganizer.presentation.people.vm.RateInteractionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val peopleDataSourceModule = module {
    factory<FileDataSource> { DeviceFileDataSource(get()) }
    factory<InteractionDataSource> { UserInteractionDataSource(get()) }
    factory<PeopleDataSource> { UserCommunityDataSource(get()) }
}

val peopleRepositoryModule = module {
    factory { FileRepository(get()) }
    factory { InteractionRepository(get()) }
    factory { PeopleRepository(get()) }
}

val peopleUseCaseModule = module {
    factory { AddInteractionEntry(get()) }
    factory { AddPerson(get()) }
    factory { GetAllPeople(get()) }
    factory { GetImageBitmap(get()) }
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

val peopleViewModelModule = module {
    viewModel { AddPersonViewModel(addPersonUseCase = get(), getImageBitmapUseCase = get()) }
    viewModel { PeopleViewModel(get()) }
    viewModel { PersonEntryViewModel() }
    viewModel { (id: Long) -> RateInteractionViewModel(get(), id) }
}