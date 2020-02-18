package one.gypsy.neatorganizer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.gypsy.neatorganizer.data.database.dao.InteractionDao
import one.gypsy.neatorganizer.data.database.dao.PersonDao
import one.gypsy.neatorganizer.data.database.entity.InteractionEntryEntity
import one.gypsy.neatorganizer.data.database.entity.PersonEntity

@Database(entities = [PersonEntity::class, InteractionEntryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class OrganizerDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun interactionDao(): InteractionDao
}