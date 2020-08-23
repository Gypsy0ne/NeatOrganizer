package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Entity

@Entity(primaryKeys = ["snapshotTaskId", "snapshotDayId"])
class RoutineSnapshotDayTaskCrossRefEntity(
    val snapshotTaskId: Long,
    val snapshotDayId: Long
)