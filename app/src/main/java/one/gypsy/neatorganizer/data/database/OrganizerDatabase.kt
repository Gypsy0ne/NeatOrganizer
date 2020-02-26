package one.gypsy.neatorganizer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity

@Database(entities = [PersonEntity::class, InteractionEntryEntity::class, SingleTaskEntity::class, SingleTaskGroupEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class OrganizerDatabase: RoomDatabase() {
    abstract fun singleTaskGroupsDao(): SingleTaskGroupsDao
    abstract fun singleTasksDao(): SingleTasksDao
    abstract fun personDao(): PeopleDao
    abstract fun interactionDao(): InteractionsDao
}