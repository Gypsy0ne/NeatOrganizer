package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduledRoutineWithTasks(
    @Embedded val routine: RoutineEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "routineId"
    ) val tasks: List<RoutineTaskEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "routineId"
    ) val schedule: RoutineScheduleEntity
)