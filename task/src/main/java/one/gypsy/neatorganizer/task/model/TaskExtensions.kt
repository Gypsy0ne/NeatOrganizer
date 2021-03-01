package one.gypsy.neatorganizer.task.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntryDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntryDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto

internal fun SingleTaskGroupWithTasksDto.toTaskListHeader(expanded: Boolean) =
    TaskListItem.TaskListHeader(
        id = this.taskGroup.id,
        title = this.taskGroup.name,
        subItemsCount = this.tasks.size,
        expanded = expanded,
        createdAt = this.taskGroup.createdAt
    )

internal fun SingleTaskEntryDto.toTaskListSubItem() = TaskListItem.TaskListSubItem(
    id = this.id,
    title = this.name,
    groupId = this.groupId,
    done = this.done,
    createdAt = this.createdAt
)

internal fun SingleTaskGroupEntryDto.toTaskGroupEntryItem() =
    TaskGroupEntryItem(id, name, tasksCount, tasksDone)

internal fun SingleTaskEntryDto.toTaskEntryWidgetItem() = TaskEntryWidgetItem(name, done, createdAt)
