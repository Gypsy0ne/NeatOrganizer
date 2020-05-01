package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTaskGroupsRepository
import one.gypsy.neatorganizer.data.repositories.tasks.SingleTasksRepository
import one.gypsy.neatorganizer.domain.datasource.people.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserCommunityDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserInteractionDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTasksDataSource

@Module
class RepositoryModule {

    @Provides
    fun providePeopleRepository(dataSource: UserCommunityDataSource): PeopleRepository {
        return PeopleRepository(
            dataSource
        )
    }

    @Provides
    fun provideInteractionRepository(dataSource: UserInteractionDataSource): InteractionRepository {
        return InteractionRepository(
            dataSource
        )
    }

    @Provides
    fun provideFileRepository(dataSource: DeviceFileDataSource): FileRepository {
        return FileRepository(
            dataSource
        )
    }

    @Provides
    fun provideSingleTaskGroupsRepository(dataSource: UserSingleTaskGroupsDataSource): SingleTaskGroupsRepository {
        return SingleTaskGroupsRepository(
            dataSource
        )
    }

    @Provides
    fun provideSingleTasksRepository(dataSource: UserSingleTasksDataSource): SingleTasksRepository {
        return SingleTasksRepository(
            dataSource
        )
    }
}