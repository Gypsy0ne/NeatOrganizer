package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.database.entity.routines.RoutineEntity
import one.gypsy.neatorganizer.database.entity.routines.ScheduledRoutineWithTasks
import one.gypsy.neatorganizer.utils.Timestamped

data class RoutineWithTasks(
    val id: Long = 0,
    val name: String,
    val schedule: RoutineSchedule,
    val tasks: List<RoutineTaskEntry>,
    override val createdAt: Long
) : Timestamped

fun RoutineWithTasks.toRoutineEntity() =
    RoutineEntity(
        name = this.name,
        id = this.id,
        createdAt = this.createdAt
    )

fun RoutineWithTasks.toScheduledRoutineEntity() =
    ScheduledRoutineWithTasks(
        routine = this.toRoutineEntity(),
        schedule = this.schedule.toRoutineScheduleEntity(),
        tasks = this.tasks.map { it.toRoutineTaskEntity() }
    )
