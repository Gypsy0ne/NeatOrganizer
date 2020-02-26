package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.*
import one.gypsy.neatorganizer.domain.datasource.*

@Module
class RepositoryModule {

    @Provides
    fun providePeopleRepository(dataSource: UserCommunityDataSource): PeopleRepository {
        return PeopleRepository(dataSource)
    }

    @Provides
    fun provideInteractionRepository(dataSource: UserInteractionDataSource): InteractionRepository {
        return InteractionRepository(dataSource)
    }

    @Provides
    fun provideFileRepository(dataSource: DeviceFileDataSource): FileRepository {
        return FileRepository(dataSource)
    }

    @Provides
    fun provideSingleTaskGroupsRepository(dataSource: UserSingleTaskGroupsDataSource): SingleTaskGroupsRepository{
        return SingleTaskGroupsRepository(dataSource)
    }

    @Provides
    fun provideSingleTasksRepository(dataSource: SingleTasksDataSource): SingleTasksRepository {
        return SingleTasksRepository(dataSource)
    }
}