package one.gypsy.neatorganizer.presentation.tasks.view

import one.gypsy.neatorganizer.presentation.common.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskHeaderClickListener(
    override val onExpanderClick: (headerItem: TaskListItem.TaskListHeader) -> Unit = {},
    override val onEditionSubmitClick: (headerItem: TaskListItem.TaskListHeader) -> Unit = {},
    override val onRemoveClick: (headerItem: TaskListItem.TaskListHeader) -> Unit = {}
) : HeaderClickListener<TaskListItem.TaskListHeader>