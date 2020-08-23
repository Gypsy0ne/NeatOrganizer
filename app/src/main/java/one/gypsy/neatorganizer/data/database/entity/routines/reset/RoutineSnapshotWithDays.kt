package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Embedded
import androidx.room.Relation

data class RoutineSnapshotWithDays(
    @Embedded val snapshot: RoutineSnapshotEntity,
    @Relation(
        parentColumn = "routineSnapshotId",
        entityColumn = "snapshotId"
    )
    val snapshotDays: List<RoutineSnapshotDayEntity>
)