package one.gypsy.neatorganizer.presentation.routines.model

import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry

class RoutineListMapper {

    fun mapRoutinesToListItems(
        routines: List<Routine>,
        oldList: List<RoutineListItem>
    ) =
        mutableListOf<RoutineListItem>().apply {
            val oldHeaders = oldList.filterIsInstance<RoutineListItem.RoutineListHeader>()
            routines.forEach { routine ->
                this.addAll(
                    mapRoutineToRoutineListItems(
                        routine,
                        wasHeaderExpanded(oldHeaders, routine)
                    )
                )
            }
        }

    private fun wasHeaderExpanded(
        oldHeaders: List<RoutineListItem.RoutineListHeader>,
        routine: Routine
    ) = oldHeaders.firstOrNull { it.id == routine.id }?.expanded ?: false

    private fun mapRoutineToRoutineListItems(
        routine: Routine,
        expandedHeader: Boolean = false
    ): List<RoutineListItem> =
        mutableListOf<RoutineListItem>().apply {
            with(routine.toRoutineListHeader(expandedHeader)) {
                add(this)
                addAll(mapRoutineTasksToListSubItems(routine.tasks))
            }
        }

    private fun mapRoutineTasksToListSubItems(
        routineTasks: List<RoutineTaskEntry>
    ) = List(routineTasks.size) {
        routineTasks[it].toRoutineListSubItem()
    }

    fun getVisibleItems(items: List<RoutineListItem>): List<RoutineListItem> =
        items.partition { it is RoutineListItem.RoutineListHeader }.let { partedLists ->
            mutableListOf<RoutineListItem>().apply {
                partedLists.first
                    .filterIsInstance<RoutineListItem.RoutineListHeader>()
                    .forEach { header ->
                        this.addAll(
                            getHeaderWithItemsIfExpanded(
                                header,
                                partedLists.second
                            )
                        )
                    }
            }
        }

    private fun getHeaderWithItemsIfExpanded(
        header: RoutineListItem.RoutineListHeader,
        subItems: List<RoutineListItem>
    ) = mutableListOf<RoutineListItem>().apply {
        this.add(header)
        if (header.expanded) {
            this.addAll(
                subItems.filter { shouldAddToRoutine(it, header.id) })
        }
    }

    private fun shouldAddToRoutine(
        listItem: RoutineListItem,
        headerId: Long
    ) = listItem is RoutineListItem.RoutineListSubItem && headerId == listItem.groupId

    fun updateExpansion(headerItemId: Long, oldList: List<RoutineListItem>?) =
        oldList?.map { negateExpandedIfHeader(it, headerItemId) }

    private fun negateExpandedIfHeader(
        listedItem: RoutineListItem,
        headerItemId: Long
    ) = if (listedItem is RoutineListItem.RoutineListHeader && listedItem.id == headerItemId) {
        listedItem.copy(expanded = !listedItem.expanded)
    } else {
        listedItem
    }
}