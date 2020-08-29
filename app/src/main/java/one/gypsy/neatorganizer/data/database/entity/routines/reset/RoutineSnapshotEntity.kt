package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "routine_snapshots")
data class RoutineSnapshotEntity(
    val tasksOverall: Int,
    val tasksDone: Int,
    val routinesResetDate: Date,
    @PrimaryKey(autoGenerate = true) var routineSnapshotId: Long = 0
)