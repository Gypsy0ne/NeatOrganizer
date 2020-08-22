package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class RoutineSnapshotDayWithTasks(
    @Embedded val snapshotDay: RoutineSnapshotDayEntity,
    @Relation(
        parentColumn = "snapshotDayId",
        entity = RoutineSnapshotTaskEntity::class,
        entityColumn = "snapshotTaskId",
        associateBy = Junction(
            RoutineSnapshotDayTaskCrossRef::class,
            parentColumn = "snapshotDayId",
            entityColumn = "snapshotTaskId"
        )
    )
    val tasks: List<RoutineSnapshotTaskEntity>
)