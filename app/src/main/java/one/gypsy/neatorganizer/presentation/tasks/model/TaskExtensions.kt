package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup

fun SingleTaskGroup.toTaskListHeader(expanded: Boolean) = TaskListItem.TaskListHeader(
    id = this.id,
    name = this.name,
    subItemsCount = this.tasks?.size ?: 0,
    expanded = expanded
)

fun SingleTaskEntry.toTaskListSubItem(visible: Boolean) = TaskListItem.TaskListSubItem(
    id = this.id,
    name = this.name,
    visible = visible,
    groupId = this.groupId,
    done = this.done
)