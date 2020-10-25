package one.gypsy.neatorganizer.presentation.tasks.view

import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem

class TaskSubItemClickListener(
    override val onDoneClick: (subItem: TaskListItem.TaskListSubItem) -> Unit = {},
    override val onEditionSubmitClick: (subItem: TaskListItem.TaskListSubItem) -> Unit = {},
    override val onRemoveClick: (subItem: TaskListItem.TaskListSubItem) -> Unit = {}
) : SubItemClickListener<TaskListItem.TaskListSubItem>