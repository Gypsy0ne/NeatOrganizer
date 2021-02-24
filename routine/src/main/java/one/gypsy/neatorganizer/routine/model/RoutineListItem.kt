package one.gypsy.neatorganizer.routine.model

import one.gypsy.neatorganizer.core.listing.Editable
import one.gypsy.neatorganizer.core.listing.HeaderItem
import one.gypsy.neatorganizer.core.listing.Listed
import one.gypsy.neatorganizer.core.listing.SubItem
import one.gypsy.neatorganizer.domain.dto.routines.RoutineScheduleDto
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntryDto
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto

internal sealed class RoutineListItem(
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

internal fun RoutineListItem.RoutineListHeader.toRoutine(
    schedule: RoutineScheduleDto = RoutineScheduleDto.EMPTY,
    tasks: List<RoutineTaskEntryDto> = emptyList()
) = RoutineWithTasksDto(this.id, this.title, schedule, tasks, createdAt = this.createdAt)

internal fun RoutineListItem.RoutineListHeader.getRoutineSchedule() =
    RoutineScheduleDto(routineId = this.id, scheduledDays = this.scheduleDays)

internal fun RoutineListItem.RoutineListSubItem.toRoutineTask() =
    RoutineTaskEntryDto(
        id = this.id,
        routineId = this.groupId,
        name = this.title,
        done = this.done,
        createdAt = this.createdAt
    )
