package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup

fun SingleTaskGroup.toTaskListHeader(expanded: Boolean) = TaskListItem.TaskListHeader(
    this.id,
    this.name,
    true,
    this.id,
    this.tasks?.size ?: 0,
    expanded = expanded
)

fun SingleTaskEntry.toTaskListSubItem(visible: Boolean) = TaskListItem.TaskListSubItem(
    this.id,
    this.name,
    visible,
    this.groupId,
    this.done
)