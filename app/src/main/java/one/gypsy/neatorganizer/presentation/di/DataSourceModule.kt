package one.gypsy.neatorganizer.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.dao.PersonDao
import one.gypsy.neatorganizer.domain.datasource.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.UserCommunityDataSource

@Module
class DataSourceModule {

    @Provides
    fun provideUserCommunityDataSource(personDao: PersonDao): UserCommunityDataSource {
        return UserCommunityDataSource(personDao)
    }

    @Provides
    fun provideDeviceFileDataSource(application: Application): DeviceFileDataSource {
        return DeviceFileDataSource(application)
    }
}