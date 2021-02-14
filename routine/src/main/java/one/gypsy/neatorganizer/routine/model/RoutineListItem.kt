package one.gypsy.neatorganizer.routine.model

import one.gypsy.neatorganizer.core.listing.Editable
import one.gypsy.neatorganizer.core.listing.HeaderItem
import one.gypsy.neatorganizer.core.listing.Listed
import one.gypsy.neatorganizer.core.listing.SubItem
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks

sealed class RoutineListItem(
    override val id: Long,
    override val title: String,
    override val edited: Boolean
) : Listed, Editable {
    data class RoutineListHeader(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false,
        val scheduleDays: List<Boolean>,
        val createdAt: Long
    ) : RoutineListItem(id = id, title = title, edited = edited), HeaderItem

    data class RoutineListSubItem(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false,
        val createdAt: Long
    ) : RoutineListItem(id = id, title = title, edited = edited), SubItem
}

fun RoutineListItem.RoutineListHeader.toRoutine(
    schedule: RoutineSchedule = RoutineSchedule.EMPTY,
    tasks: List<RoutineTaskEntry> = emptyList()
) = RoutineWithTasks(this.id, this.title, schedule, tasks, createdAt = this.createdAt)

fun RoutineListItem.RoutineListHeader.getRoutineSchedule() =
    RoutineSchedule(routineId = this.id, scheduledDays = this.scheduleDays)

fun RoutineListItem.RoutineListSubItem.toRoutineTask() =
    RoutineTaskEntry(
        id = this.id,
        routineId = this.groupId,
        name = this.title,
        done = this.done,
        createdAt = this.createdAt
    )
