package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.domain.interactors.people.*

@Module
class UseCaseModule {

    // people
    @Provides
    fun provideAddPersonUseCase(peopleRepository: PeopleRepository): AddPerson {
        return AddPerson(
            peopleRepository
        )
    }

    @Provides
    fun provideGetAllPeopleUseCase(peopleRepository: PeopleRepository): GetAllPeople {
        return GetAllPeople(
            peopleRepository
        )
    }

    @Provides
    fun provideGetImageBitmapUseCase(fileRepository: FileRepository): GetImageBitmap {
        return GetImageBitmap(
            fileRepository
        )
    }

    @Provides
    fun provideAddInteractionEntryUseCase(interactionRepository: InteractionRepository): AddInteractionEntry {
        return AddInteractionEntry(
            interactionRepository
        )
    }

    //TODO inject personHistoryRepository here
    @Provides
    fun provideGetPersonProfile(peopleRepository: PeopleRepository): GetPersonHistory {
        return GetPersonHistory(
            peopleRepository
        )
    }

}