package one.gypsy.neatorganizer.presentation.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import one.gypsy.neatorganizer.data.database.OrganizerDatabase
import one.gypsy.neatorganizer.data.database.dao.PersonDao
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): OrganizerDatabase {
        return Room.databaseBuilder(application, OrganizerDatabase::class.java, "NeatOrganizer.db").build()
    }

    @Provides
    fun providePeopleRepository(organizerDatabase: OrganizerDatabase): PersonDao {
        return organizerDatabase.personDao()
    }
}