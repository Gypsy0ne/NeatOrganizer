package one.gypsy.neatorganizer.presentation.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.repositories.FileRepository
import one.gypsy.neatorganizer.data.repositories.PeopleRepository
import one.gypsy.neatorganizer.domain.datasource.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.UserCommunityDataSource

@Module
class RepositoryModule {

    @Provides
    fun providePeopleRepository(dataSource: UserCommunityDataSource): PeopleRepository {
        return PeopleRepository(dataSource)
    }

    @Provides
    fun provideFileRepository(dataSource: DeviceFileDataSource): FileRepository {
        return FileRepository(dataSource)
    }
}