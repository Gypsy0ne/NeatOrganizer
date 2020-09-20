package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry

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

fun SingleTaskGroupEntry.toTaskGroupEntryItem() =
    TaskGroupEntryItem(id, name, tasksCount, tasksDone)