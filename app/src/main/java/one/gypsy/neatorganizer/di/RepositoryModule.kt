package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.FileRepository
import one.gypsy.neatorganizer.data.PeopleDataSource
import one.gypsy.neatorganizer.data.PeopleRepository
import one.gypsy.neatorganizer.framework.DeviceFileDataSource
import one.gypsy.neatorganizer.framework.UserCommunityDataSource
import javax.sql.DataSource

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