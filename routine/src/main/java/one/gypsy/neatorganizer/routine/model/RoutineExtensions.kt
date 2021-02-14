package one.gypsy.neatorganizer.routine.model

import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks

fun RoutineWithTasks.toRoutineListHeader(expanded: Boolean = false) =
    RoutineListItem.RoutineListHeader(
        id = this.id,
        title = this.name,
        subItemsCount = this.tasks.size,
        expanded = expanded,
        scheduleDays = this.schedule.scheduledDays,
        createdAt = this.createdAt
    )

fun RoutineTaskEntry.toRoutineListSubItem() = RoutineListItem.RoutineListSubItem(
    id = this.id,
    title = this.name,
    done = this.done,
    groupId = this.routineId,
    createdAt = this.createdAt
)
