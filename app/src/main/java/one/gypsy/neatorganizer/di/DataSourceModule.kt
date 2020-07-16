package one.gypsy.neatorganizer.di

import android.app.Application
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.domain.datasource.people.DeviceFileDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserCommunityDataSource
import one.gypsy.neatorganizer.domain.datasource.people.UserInteractionDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.UserRoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.UserRoutineTasksDataSource
import one.gypsy.neatorganizer.domain.datasource.routines.UserRoutinesDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.datasource.tasks.UserSingleTasksDataSource

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

    @Provides
    fun provideUserSingleTaskGroupsDataSource(singleTaskGroupsDao: SingleTaskGroupsDao): UserSingleTaskGroupsDataSource {
        return UserSingleTaskGroupsDataSource(
            singleTaskGroupsDao
        )
    }

    @Provides
    fun provideUserSingleTasksDataSource(singleTasksDao: SingleTasksDao): UserSingleTasksDataSource {
        return UserSingleTasksDataSource(
            singleTasksDao
        )
    }

    @Provides
    fun provideUserRoutinesDataSource(routinesDao: RoutinesDao): UserRoutinesDataSource {
        return UserRoutinesDataSource(
            routinesDao
        )
    }

    @Provides
    fun provideUserRoutineTasksDataSource(routineTasksDao: RoutineTasksDao): UserRoutineTasksDataSource {
        return UserRoutineTasksDataSource(
            routineTasksDao
        )
    }

    @Provides
    fun provideUserRoutineSchedules(routineSchedulesDao: RoutineSchedulesDao): UserRoutineSchedulesDataSource {
        return UserRoutineSchedulesDataSource(
            routineSchedulesDao
        )
    }
}