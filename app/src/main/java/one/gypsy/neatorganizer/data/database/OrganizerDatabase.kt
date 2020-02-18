package one.gypsy.neatorganizer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.gypsy.neatorganizer.data.database.dao.InteractionDao
import one.gypsy.neatorganizer.data.database.dao.PeopleDao
import one.gypsy.neatorganizer.data.database.dao.SingleTaskDao
import one.gypsy.neatorganizer.data.database.entity.InteractionEntryEntity
import one.gypsy.neatorganizer.data.database.entity.PersonEntity
import one.gypsy.neatorganizer.data.database.entity.SingleTaskEntity

@Database(entities = [PersonEntity::class, InteractionEntryEntity::class, SingleTaskEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class OrganizerDatabase: RoomDatabase() {
    abstract fun singleTaskDao(): SingleTaskDao
    abstract fun personDao(): PeopleDao
    abstract fun interactionDao(): InteractionDao
}