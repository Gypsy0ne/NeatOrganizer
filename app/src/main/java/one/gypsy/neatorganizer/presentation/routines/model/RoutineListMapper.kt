package one.gypsy.neatorganizer.presentation.routines.model

import one.gypsy.neatorganizer.domain.dto.routines.Routine

class RoutineListMapper {
    fun mapRoutinesToRoutineListItems(routines: List<Routine>): List<RoutineListItem> =
        routines.map {
            it.toRoutineListHeader(false)
        }
}