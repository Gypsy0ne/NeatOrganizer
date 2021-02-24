package one.gypsy.neatorganizer.task.view

import one.gypsy.neatorganizer.core.listing.SubItemClickListener
import one.gypsy.neatorganizer.task.model.TaskListItem

internal class TaskSubItemClickListener(
    override val onDoneClick: (subItem: TaskListItem.TaskListSubItem) -> Unit = {},
    override val onEditionSubmitClick: (subItem: TaskListItem.TaskListSubItem) -> Unit = {},
    override val onRemoveClick: (subItem: TaskListItem.TaskListSubItem) -> Unit = {}
) : SubItemClickListener<TaskListItem.TaskListSubItem>
