package one.gypsy.neatorganizer.presentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): OrganizerDatabase {
        return Room.databaseBuilder(application, OrganizerDatabase::class.java, "NeatOrganizer65.db").build()
    }

    @Provides
    fun providePeopleRepository(organizerDatabase: OrganizerDatabase): PeopleDao {
        return organizerDatabase.personDao()
    }

    @Provides
    fun provideInteractionRepository(organizerDatabase: OrganizerDatabase): InteractionsDao {
        return organizerDatabase.interactionDao()
    }

    @Provides
    fun provideSingleTaskGroupsRepository(organizerDatabase: OrganizerDatabase): SingleTaskGroupsDao {
        return organizerDatabase.singleTaskGroupsDao()
    }

    @Provides
    fun provideSingleTasksRepository(organizerDatabase: OrganizerDatabase): SingleTasksDao {
        return organizerDatabase.singleTasksDao()
    }
}