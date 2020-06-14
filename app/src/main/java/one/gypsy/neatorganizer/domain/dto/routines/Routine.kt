package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.database.entity.routines.RoutineEntity
import one.gypsy.neatorganizer.data.database.entity.routines.ScheduledRoutineWithTasks

data class Routine(
    val id: Long = 0,
    val name: String,
    val schedule: RoutineSchedule,
    val tasks: List<RoutineTaskEntry>
)

fun Routine.toRoutineEntity() = RoutineEntity(name = this.name, id = this.id)

fun Routine.toScheduledRoutineEntity() = ScheduledRoutineWithTasks(
    routine = this.toRoutineEntity(),
    schedule = this.schedule.toRoutineScheduleEntity(),
    tasks = this.tasks.map { it.toRoutineTaskEntity() })