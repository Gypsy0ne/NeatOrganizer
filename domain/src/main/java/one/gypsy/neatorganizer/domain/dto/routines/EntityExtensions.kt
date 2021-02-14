package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.database.entity.routines.RoutineScheduleEntity
import one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity
import one.gypsy.neatorganizer.database.entity.routines.ScheduledRoutineWithTasks

fun RoutineScheduleEntity.toRoutineSchedule() = RoutineSchedule(
    routineId = this.routineId,
    scheduledDays = listOf(
        monday,
        tuesday,
        wednesday,
        thursday,
        friday,
        saturday,
        sunday
    )
)

fun RoutineTaskEntity.toRoutineTaskEntry() =
    RoutineTaskEntry(
        id = this.id,
        routineId = this.routineId,
        name = this.name,
        done = this.done,
        createdAt = this.createdAt
    )

fun ScheduledRoutineWithTasks.toRoutineWithTasks() =
    RoutineWithTasks(
        id = this.routine.id,
        name = this.routine.name,
        schedule = this.schedule.toRoutineSchedule()
            ?: RoutineSchedule.EMPTY.copy(routineId = this.routine.id),
        tasks = this.tasks.map {
            it.toRoutineTaskEntry()
        },
        createdAt = routine.createdAt
    )
