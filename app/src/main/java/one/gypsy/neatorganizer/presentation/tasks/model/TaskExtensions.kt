package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup

fun SingleTaskGroup.toTaskListHeader() = TaskListItem.TaskListHeader(
    this.id,
    this.name,
    true,
    this.id,
    this.tasks?.size ?: 0
)

fun SingleTaskEntry.toTaskListSubItem() = TaskListItem.TaskListSubItem(
    this.id,
    this.name,
    false,
    this.id,
    this.done
)