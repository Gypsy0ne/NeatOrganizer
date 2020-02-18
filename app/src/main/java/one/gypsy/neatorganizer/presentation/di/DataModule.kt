package one.gypsy.neatorganizer.presentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.InteractionDao
import one.gypsy.neatorganizer.data.database.dao.PeopleDao
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): OrganizerDatabase {
        return Room.databaseBuilder(application, OrganizerDatabase::class.java, "NeatOrganizer69.db").build()
    }

    @Provides
    fun providePeopleRepository(organizerDatabase: OrganizerDatabase): PeopleDao {
        return organizerDatabase.personDao()
    }

    @Provides
    fun provideInteractionRepository(organizerDatabase: OrganizerDatabase): InteractionDao {
        return organizerDatabase.interactionDao()
    }
}