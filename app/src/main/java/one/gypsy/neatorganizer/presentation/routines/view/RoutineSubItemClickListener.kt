package one.gypsy.neatorganizer.presentation.routines.view

import one.gypsy.neatorganizer.presentation.common.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

class RoutineSubItemClickListener(
    override val onDoneClick: (subItem: RoutineListItem.RoutineListSubItem) -> Unit = {},
    override val onEditionSubmitClick: (subItem: RoutineListItem.RoutineListSubItem) -> Unit = {},
    override val onRemoveClick: (subItem: RoutineListItem.RoutineListSubItem) -> Unit = {}
) : SubItemClickListener<RoutineListItem.RoutineListSubItem>