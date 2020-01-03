package one.gypsy.neatorganizer.di

import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.database.dao.PersonDao
import one.gypsy.neatorganizer.framework.UserCommunityDataSource

@Module
class DataSourceModule {

    @Provides
    fun provideUserCommunityDataSource(personDao: PersonDao): UserCommunityDataSource {
        return UserCommunityDataSource(personDao)
    }
}