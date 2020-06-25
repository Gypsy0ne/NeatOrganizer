package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup

fun SingleTaskGroup.toTaskListHeader(expanded: Boolean) = TaskListItem.TaskListHeader(
    id = this.id,
    name = this.name,
    subItemsCount = this.tasks.size,
    expanded = expanded
)

fun SingleTaskEntry.toTaskListSubItem() = TaskListItem.TaskListSubItem(
    id = this.id,
    name = this.name,
    groupId = this.groupId,
    done = this.done
)