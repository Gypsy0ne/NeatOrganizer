package one.gypsy.neatorganizer.presentation.routines.view

import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem

class RoutineHeaderClickListener(
    override val onExpanderClick: (headerItem: RoutineListItem.RoutineListHeader) -> Unit = {},
    override val onEditionSubmitClick: (headerItem: RoutineListItem.RoutineListHeader) -> Unit = {},
    override val onRemoveClick: (headerItem: RoutineListItem.RoutineListHeader) -> Unit = {}
) : HeaderClickListener<RoutineListItem.RoutineListHeader>