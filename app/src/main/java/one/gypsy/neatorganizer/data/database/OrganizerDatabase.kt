package one.gypsy.neatorganizer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.dao.people.PeopleDao
import one.gypsy.neatorganizer.data.database.dao.routines.*
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineEntity
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineScheduleEntity
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineTaskEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotDayEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotDayTaskCrossRefEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotTaskEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity

@Database(
    entities = [PersonEntity::class,
        InteractionEntryEntity::class,
        SingleTaskEntity::class,
        SingleTaskGroupEntity::class,
        RoutineEntity::class,
        RoutineScheduleEntity::class,
        RoutineTaskEntity::class,
        RoutineSnapshotDayEntity::class,
        RoutineSnapshotDayTaskCrossRefEntity::class,
        RoutineSnapshotEntity::class,
        RoutineSnapshotTaskEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class OrganizerDatabase : RoomDatabase() {
    abstract fun routineSnapshotDaysDao(): RoutineSnapshotDaysDao
    abstract fun routineSnapshotsDao(): RoutineSnapshotsDao
    abstract fun routineSnapshotTasksDao(): RoutineSnapshotTasksDao
    abstract fun singleTaskGroupsDao(): SingleTaskGroupsDao
    abstract fun singleTasksDao(): SingleTasksDao
    abstract fun personDao(): PeopleDao
    abstract fun interactionDao(): InteractionsDao
    abstract fun routinesDao(): RoutinesDao
    abstract fun routinesSchedulesDao(): RoutineSchedulesDao
    abstract fun routineTasksDao(): RoutineTasksDao
}