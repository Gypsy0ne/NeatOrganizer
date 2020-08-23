package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Embedded
import androidx.room.Relation


data class RoutineSnapshotsWithDaysAndTasks(
    @Embedded val snapshot: RoutineSnapshotEntity,
    @Relation(
        entity = RoutineSnapshotDayEntity::class,
        parentColumn = "routineSnapshotId",
        entityColumn = "snapshotId"
    )
    val days: List<RoutineSnapshotDayWithTasks>
)