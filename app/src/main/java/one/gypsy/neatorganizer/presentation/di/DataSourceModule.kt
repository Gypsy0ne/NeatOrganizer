package one.gypsy.neatorganizer.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.domain.datasource.*

@Module
class DataSourceModule {

    @Provides
    fun provideUserCommunityDataSource(peopleDao: PeopleDao): UserCommunityDataSource {
        return UserCommunityDataSource(peopleDao)
    }

    @Provides
    fun provideDeviceFileDataSource(application: Application): DeviceFileDataSource {
        return DeviceFileDataSource(application)
    }

    @Provides
    fun provideUserInteractionDataSource(interactionsDao: InteractionsDao): UserInteractionDataSource {
        return UserInteractionDataSource(interactionsDao)
    }

    @Provides
    fun provideUserSingleTaskGroupsDataSource(singleTaskGroupsDao: SingleTaskGroupsDao): UserSingleTaskGroupsDataSource{
        return UserSingleTaskGroupsDataSource(singleTaskGroupsDao)
    }

    @Provides
    fun provideUserSingleTasksDataSource(singleTasksDao: SingleTasksDao): UserSingleTasksDataSource {
        return UserSingleTasksDataSource(singleTasksDao)
    }
}