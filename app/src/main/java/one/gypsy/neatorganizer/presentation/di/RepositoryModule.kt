package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.FileRepository
import one.gypsy.neatorganizer.data.repositories.InteractionRepository
import one.gypsy.neatorganizer.data.repositories.PeopleRepository
import one.gypsy.neatorganizer.domain.datasource.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.UserCommunityDataSource
import one.gypsy.neatorganizer.domain.datasource.UserInteractionDataSource

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
}