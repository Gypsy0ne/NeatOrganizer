package one.gypsy.neatorganizer.data.database.entity.routines.reset

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class RoutineSnapshotDayWithTasks(
    @Embedded val snapshotDay: RoutineSnapshotDayEntity,
    @Relation(
        parentColumn = "snapshotDayId",
        entityColumn = "snapshotTaskId",
        associateBy = Junction(
            RoutineSnapshotDayTaskCrossRefEntity::class
        )
    )
    val tasks: List<RoutineSnapshotTaskEntity>
)