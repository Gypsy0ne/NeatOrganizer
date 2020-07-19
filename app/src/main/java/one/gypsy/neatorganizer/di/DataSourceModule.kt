package one.gypsy.neatorganizer.di

import android.app.Application
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.domain.datasource.people.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserCommunityDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserInteractionDataSource

@Module
class DataSourceModule {

    @Provides
    fun provideUserCommunityDataSource(peopleDao: PeopleDao): UserCommunityDataSource {
        return UserCommunityDataSource(
            peopleDao
        )
    }

    @Provides
    fun provideDeviceFileDataSource(application: Application): DeviceFileDataSource {
        return DeviceFileDataSource(
            application
        )
    }

    @Provides
    fun provideUserInteractionDataSource(interactionsDao: InteractionsDao): UserInteractionDataSource {
        return UserInteractionDataSource(
            interactionsDao
        )
    }

}