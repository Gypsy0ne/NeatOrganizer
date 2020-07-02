package one.gypsy.neatorganizer.data.database.entity.routines

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule

data class ScheduledRoutineWithTasks(
    @Embedded val routine: RoutineEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "routineId"
    ) val tasks: List<RoutineTaskEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "routineId"
    ) val schedule: RoutineScheduleEntity?
)

fun ScheduledRoutineWithTasks.toRoutine() =
    Routine(
        id = this.routine.id,
        name = this.routine.name,
        schedule = this.schedule?.toRoutineSchedule()
            ?: RoutineSchedule.EMPTY.copy(routineId = this.routine.id),
        tasks = this.tasks.map {
            it.toRoutineTaskEntry()
        }
    )