package one.gypsy.neatorganizer.routine.view

import one.gypsy.neatorganizer.core.listing.HeaderClickListener
import one.gypsy.neatorganizer.routine.model.RoutineListItem

class RoutineHeaderClickListener(
    override val onExpanderClick: (headerItem: RoutineListItem.RoutineListHeader) -> Unit = {},
    override val onEditionSubmitClick: (headerItem: RoutineListItem.RoutineListHeader) -> Unit = {},
    override val onRemoveClick: (headerItem: RoutineListItem.RoutineListHeader) -> Unit = {}
) : HeaderClickListener<RoutineListItem.RoutineListHeader>
