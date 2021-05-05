package one.gypsy.neatorganizer.database.entity.routines.reset

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "routine_snapshots")
data class RoutineSnapshotEntity(
    val routinesResetDate: Date,
    @PrimaryKey(autoGenerate = true) var routineSnapshotId: Long = 0
)
