package one.gypsy.neatorganizer.task.view

import one.gypsy.neatorganizer.core.listing.HeaderClickListener
import one.gypsy.neatorganizer.task.model.TaskListItem

class TaskHeaderClickListener(
    override val onExpanderClick: (headerItem: TaskListItem.TaskListHeader) -> Unit = {},
    override val onEditionSubmitClick: (headerItem: TaskListItem.TaskListHeader) -> Unit = {},
    override val onRemoveClick: (headerItem: TaskListItem.TaskListHeader) -> Unit = {}
) : HeaderClickListener<TaskListItem.TaskListHeader>
