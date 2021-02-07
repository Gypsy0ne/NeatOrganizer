package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.database.entity.routines.ScheduledRoutineWithTasks

data class RoutineWithTasks(
    val id: Long = 0,
    val name: String,
    val schedule: RoutineSchedule,
    val tasks: List<RoutineTaskEntry>,
    val createdAt: Long
)

fun RoutineWithTasks.toRoutineEntity() =
    one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
        name = this.name,
        id = this.id,
        createdAt = this.createdAt
    )

fun RoutineWithTasks.toScheduledRoutineEntity() = ScheduledRoutineWithTasks(
    routine = this.toRoutineEntity(),
    schedule = this.schedule.toRoutineScheduleEntity(),
    tasks = this.tasks.map { it.toRoutineTaskEntity() }
)
