package one.gypsy.neatorganizer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.gypsy.neatorganizer.data.database.dao.notes.NoteWidgetsDao
import one.gypsy.neatorganizer.data.database.dao.notes.NotesDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSnapshotsDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import one.gypsy.neatorganizer.data.database.entity.notes.NoteEntity
import one.gypsy.neatorganizer.data.database.entity.notes.NoteWidgetEntity
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineEntity
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineScheduleEntity
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineTaskEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.TaskWidgetEntity

@Database(
    entities = [
        SingleTaskEntity::class,
        SingleTaskGroupEntity::class,
        RoutineEntity::class,
        RoutineScheduleEntity::class,
        RoutineTaskEntity::class,
        RoutineSnapshotEntity::class,
        TaskWidgetEntity::class,
        NoteEntity::class,
        NoteWidgetEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class OrganizerDatabase : RoomDatabase() {
    abstract fun routineSnapshotsDao(): RoutineSnapshotsDao
    abstract fun singleTaskGroupsDao(): SingleTaskGroupsDao
    abstract fun singleTasksDao(): SingleTasksDao
    abstract fun routinesDao(): RoutinesDao
    abstract fun routinesSchedulesDao(): RoutineSchedulesDao
    abstract fun routineTasksDao(): RoutineTasksDao
    abstract fun taskWidgetDao(): TaskWidgetsDao
    abstract fun notesDao(): NotesDao
    abstract fun noteWidgetDao(): NoteWidgetsDao
}
