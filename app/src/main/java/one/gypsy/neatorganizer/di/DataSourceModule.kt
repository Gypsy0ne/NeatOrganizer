package one.gypsy.neatorganizer.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.database.dao.PersonDao
import one.gypsy.neatorganizer.framework.DeviceFileDataSource
import one.gypsy.neatorganizer.framework.UserCommunityDataSource

@Module
class DataSourceModule {

    @Provides
    fun provideUserCommunityDataSource(personDao: PersonDao): UserCommunityDataSource {
        return UserCommunityDataSource(personDao)
    }

    @Provides
    fun provideDeviceFileDataSource(application: Application): DeviceFileDataSource {
        return  DeviceFileDataSource(application)
    }
}