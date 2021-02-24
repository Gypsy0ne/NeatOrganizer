package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineWithTasks

data class RoutineWithTasksDto(
    val id: Long = 0,
    val name: String,
    val schedule: RoutineScheduleDto,
    val tasks: List<RoutineTaskEntryDto>,
    val createdAt: Long
)

internal fun RoutineWithTasksDto.toRoutineWithTasks() =
    RoutineWithTasks(
        name = this.name,
        id = this.id,
        createdAt = this.createdAt,
        tasks = tasks.map { it.toRoutineTaskEntry() },
        schedule = schedule.toRoutineSchedule()
    )
