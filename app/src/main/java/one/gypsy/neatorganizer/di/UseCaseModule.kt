package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.interactors.people.*
import one.gypsy.neatorganizer.domain.interactors.routines.ResetAllRoutineTasks
import one.gypsy.neatorganizer.domain.interactors.tasks.*

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

    // tasks
    @Provides
    fun provideAddSingleTaskGroup(singleTaskGroupsRepository: SingleTaskGroupsRepository): AddTaskGroup {
        return AddTaskGroup(
            singleTaskGroupsRepository
        )
    }

    @Provides
    fun provideAddSingleTask(singleTasksRepository: SingleTasksRepository): AddSingleTask {
        return AddSingleTask(
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
    fun provideUpdateSingleTask(singleTasksRepository: SingleTasksRepository): UpdateSingleTask {
        return UpdateSingleTask(
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
    fun provideRemoveSingleTask(singleTasksRepository: SingleTasksRepository): RemoveSingleTask {
        return RemoveSingleTask(
            singleTasksRepository
        )
    }

    //routines

    @Provides
    fun provideResetAllRoutineTasks(routineTasksRepository: RoutineTasksRepository): ResetAllRoutineTasks {
        return ResetAllRoutineTasks(routineTasksRepository)
    }


}