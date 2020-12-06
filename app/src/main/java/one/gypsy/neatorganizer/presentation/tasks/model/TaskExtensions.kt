package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks

fun SingleTaskGroupWithTasks.toTaskListHeader(expanded: Boolean) = TaskListItem.TaskListHeader(
    id = this.id,
    name = this.name,
    subItemsCount = this.tasks.size,
    expanded = expanded,
    createdAt = this.createdAt
)

fun SingleTaskEntry.toTaskListSubItem() = TaskListItem.TaskListSubItem(
    id = this.id,
    name = this.name,
    groupId = this.groupId,
    done = this.done,
    createdAt = this.createdAt
)

fun SingleTaskGroupEntry.toTaskGroupEntryItem() =
    TaskGroupEntryItem(id, name, tasksCount, tasksDone)

fun SingleTaskEntry.toTaskEntryWidgetItem() = TaskEntryWidgetItem(name, done)
