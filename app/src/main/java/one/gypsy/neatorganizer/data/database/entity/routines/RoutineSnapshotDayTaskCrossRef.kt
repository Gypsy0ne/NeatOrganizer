package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Entity

@Entity(primaryKeys = ["snapshotTaskId", "snapshotDayId"])
class RoutineSnapshotDayTaskCrossRef(
    val snapshotTaskId: Long,
    val snapshotDayId: Long
)