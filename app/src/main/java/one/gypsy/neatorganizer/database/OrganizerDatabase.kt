package one.gypsy.neatorganizer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.gypsy.neatorganizer.database.dao.PersonDao
import one.gypsy.neatorganizer.database.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class OrganizerDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao
}