package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class RoutineSnapshotTaskWithDays(
    @Embedded val snapshotTask: RoutineSnapshotTaskEntity,
    @Relation(
        entityColumn = "snapshotDayId",
        parentColumn = "snapshotTaskId",
        associateBy = Junction(
            RoutineSnapshotDayTaskCrossRefEntity::class
        )
    )
    val days: List<RoutineSnapshotDayEntity>
)