package one.gypsy.neatorganizer.data.model.routines

import one.gypsy.neatorganizer.database.entity.routines.ScheduledRoutineWithTasks

data class RoutineWithTasks(
    val id: Long = 0,
    val name: String,
    val schedule: RoutineSchedule,
    val tasks: List<RoutineTaskEntry>,
    val createdAt: Long
)

internal fun RoutineWithTasks.toRoutineEntity() =
    one.gypsy.neatorganizer.database.entity.routines.RoutineEntity(
        name = this.name,
        id = this.id,
        createdAt = this.createdAt
    )

internal fun RoutineWithTasks.toScheduledRoutineEntity() = ScheduledRoutineWithTasks(
    routine = this.toRoutineEntity(),
    schedule = this.schedule.toRoutineScheduleEntity(),
    tasks = this.tasks.map { it.toRoutineTaskEntity() }
)
