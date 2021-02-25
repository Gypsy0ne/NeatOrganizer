package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineSchedule
import one.gypsy.neatorganizer.data.model.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.data.model.routines.RoutineWithTasks

internal fun RoutineWithTasks.toDto() = RoutineWithTasksDto(
    id = id,
    name = name,
    schedule = schedule.toDto(),
    tasks = tasks.map { it.toDto() },
    createdAt = createdAt
)

internal fun RoutineSchedule.toDto() = RoutineScheduleDto(
    routineId = routineId,
    scheduledDays = scheduledDays
)

internal fun RoutineTaskEntry.toDto() = RoutineTaskEntryDto(
    id = id,
    routineId = routineId,
    name = name,
    done = done,
    createdAt = createdAt
)
