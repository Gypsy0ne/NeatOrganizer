package one.gypsy.neatorganizer.presentation.routines.model

import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry

class RoutineListMapper {
    //TODO create list with only visible items in list
    fun mapRoutinesToVisibleListItems(
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
            val header = routine.toRoutineListHeader(expandedHeader)
            this.add(header)
            if (header.expanded) {
                mapRoutineTasksToListSubItems(routine.tasks)
            }
        }

    private fun mapRoutineTasksToListSubItems(
        routineTasks: List<RoutineTaskEntry>
    ) = List(routineTasks.size) {
        routineTasks[it].toRoutineListSubItem()
    }

    //    private fun getVisibleRoutineTasks(routineTasks: List<RoutineTaskEntry>, expanded) =
    fun mapExpandedStateOnRoutineListItems() {

    }

    fun getExpandedRoutines(allRoutineItems: List<RoutineListItem>) {

    }
}