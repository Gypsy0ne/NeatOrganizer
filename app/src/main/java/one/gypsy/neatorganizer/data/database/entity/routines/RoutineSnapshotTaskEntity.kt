package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_snapshot_tasks")
data class RoutineSnapshotTaskEntity(
    val done: Boolean,
    val taskName: String,
    @PrimaryKey(autoGenerate = true) var snapshotTaskId: Long = 0
)