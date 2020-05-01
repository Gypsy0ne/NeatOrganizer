package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.interactors.people.*
import one.gypsy.neatorganizer.domain.interactors.tasks.*

@Module
class UseCaseModule {

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

    @Provides
    fun provideAddSingleTaskGroup(singleTaskGroupsRepository: SingleTaskGroupsRepository): AddTaskGroup {
        return AddTaskGroup(
            singleTaskGroupsRepository
        )
    }

    @Provides
    fun provideAddSingleTask(singleTasksRepository: SingleTasksRepository): AddTask {
        return AddTask(
            singleTasksRepository
        )
    }

    @Provides
    fun provideUpdateSingleTaskGroup(singleTaskGroupsRepository: SingleTaskGroupsRepository): UpdateTaskGroup {
        return UpdateTaskGroup(
            singleTaskGroupsRepository
        )
    }

    @Provides
    fun provideUpdateSingleTask(singleTasksRepository: SingleTasksRepository): UpdateTask {
        return UpdateTask(
            singleTasksRepository
        )
    }

    @Provides
    fun provideRemoveSingleTaskGroup(singleTaskGroupsRepository: SingleTaskGroupsRepository): RemoveTaskGroup {
        return RemoveTaskGroup(
            singleTaskGroupsRepository
        )
    }


    @Provides
    fun provideRemoveSingleTask(singleTasksRepository: SingleTasksRepository): RemoveTask {
        return RemoveTask(
            singleTasksRepository
        )
    }


}