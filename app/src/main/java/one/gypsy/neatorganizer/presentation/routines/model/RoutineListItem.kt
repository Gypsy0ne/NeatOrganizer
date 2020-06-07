package one.gypsy.neatorganizer.presentation.routines.model

import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.presentation.listing.HeaderItem
import one.gypsy.neatorganizer.presentation.listing.ListedItem
import one.gypsy.neatorganizer.presentation.listing.SubItem

sealed class RoutineListItem(
    override val id: Long,
    override val name: String,
    override val edited: Boolean
) : ListedItem {
    data class RoutineListHeader(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false,
        val scheduleDays: List<Boolean>
    ) : RoutineListItem(id = id, name = name, edited = edited), HeaderItem

    data class RoutineListSubItem(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false
    ) : RoutineListItem(id = id, name = name, edited = edited), SubItem
}

fun RoutineListItem.RoutineListHeader.toRoutine(
    schedule: RoutineSchedule = RoutineSchedule.EMPTY,
    tasks: List<RoutineTaskEntry> = emptyList()
) = Routine(this.id, this.name, schedule, tasks)

fun RoutineListItem.RoutineListSubItem.toRoutineTask() =
    RoutineTaskEntry(id = this.id, routineId = this.groupId, name = this.name, done = this.done)