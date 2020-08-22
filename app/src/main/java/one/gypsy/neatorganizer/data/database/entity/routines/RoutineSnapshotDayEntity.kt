package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "routine_snapshot_days")
data class RoutineSnapshotDayEntity(
    val snapshotId: Long,
    val dayId: Int,
    val snapshotDate: Date,
    @PrimaryKey(autoGenerate = true) var snapshotDayId: Long = 0
)