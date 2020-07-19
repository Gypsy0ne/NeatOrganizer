package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.people.FileRepository
import one.gypsy.neatorganizer.data.repositories.people.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.people.PeopleRepository
import one.gypsy.neatorganizer.domain.datasource.people.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserCommunityDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserInteractionDataSource

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

}