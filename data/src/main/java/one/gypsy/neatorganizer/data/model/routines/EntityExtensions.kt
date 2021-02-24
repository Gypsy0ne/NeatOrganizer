package one.gypsy.neatorganizer.data.model.routines

import one.gypsy.neatorganizer.database.entity.routines.RoutineScheduleEntity
import one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity
import one.gypsy.neatorganizer.database.entity.routines.ScheduledRoutineWithTasks

internal fun RoutineScheduleEntity.toRoutineSchedule() = RoutineSchedule(
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

internal fun RoutineTaskEntity.toRoutineTaskEntry() =
    RoutineTaskEntry(
        id = this.id,
        routineId = this.routineId,
        name = this.name,
        done = this.done,
        createdAt = this.createdAt
    )

internal fun ScheduledRoutineWithTasks.toRoutineWithTasks() =
    RoutineWithTasks(
        id = this.routine.id,
        name = this.routine.name,
        schedule = this.schedule?.toRoutineSchedule()
            ?: RoutineSchedule.EMPTY.copy(routineId = this.routine.id),
        tasks = this.tasks.map {
            it.toRoutineTaskEntry()
        },
        createdAt = routine.createdAt
    )
