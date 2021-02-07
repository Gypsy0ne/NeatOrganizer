package one.gypsy.neatorganizer.presentation.routines.model

import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks
import one.gypsy.neatorganizer.presentation.common.listing.Editable
import one.gypsy.neatorganizer.presentation.common.listing.HeaderItem
import one.gypsy.neatorganizer.presentation.common.listing.Listed
import one.gypsy.neatorganizer.presentation.common.listing.SubItem

sealed class RoutineListItem(
    override val id: Long,
    override val title: String,
    override val edited: Boolean
) : Listed, one.gypsy.neatorganizer.database.entity.Timestamped, Editable {
    data class RoutineListHeader(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false,
        val scheduleDays: List<Boolean>,
        override val createdAt: Long
    ) : RoutineListItem(id = id, title = title, edited = edited), HeaderItem

    data class RoutineListSubItem(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false,
        override val createdAt: Long
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
