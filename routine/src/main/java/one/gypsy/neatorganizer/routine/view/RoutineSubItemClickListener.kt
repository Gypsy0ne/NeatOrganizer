package one.gypsy.neatorganizer.routine.view

import one.gypsy.neatorganizer.core.listing.SubItemClickListener
import one.gypsy.neatorganizer.routine.model.RoutineListItem

internal class RoutineSubItemClickListener(
    override val onDoneClick: (subItem: RoutineListItem.RoutineListSubItem) -> Unit = {},
    override val onEditionSubmitClick: (subItem: RoutineListItem.RoutineListSubItem) -> Unit = {},
    override val onRemoveClick: (subItem: RoutineListItem.RoutineListSubItem) -> Unit = {}
) : SubItemClickListener<RoutineListItem.RoutineListSubItem>
