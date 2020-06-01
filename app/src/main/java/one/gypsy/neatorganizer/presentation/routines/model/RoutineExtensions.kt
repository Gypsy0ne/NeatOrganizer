package one.gypsy.neatorganizer.presentation.routines.model

import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry

fun Routine.toRoutineListHeader(expanded: Boolean = false) = RoutineListItem.RoutineListHeader(
    id = this.id,
    name = this.name,
    subItemsCount = this.tasks.size,
    expanded = expanded,
    scheduleDays = this.schedule.scheduledDays
)

fun RoutineTaskEntry.toRoutineListSubItem() = RoutineListItem.RoutineListSubItem(
    id = this.id,
    name = this.name,
    done = this.done,
    groupId = this.routineId
)