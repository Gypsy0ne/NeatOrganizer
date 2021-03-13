package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.data.model.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.data.model.tasks.TitledTaskWidgetEntry

internal fun SingleTaskGroupEntry.toDto() = SingleTaskGroupEntryDto(
    id = id,
    name = name,
    tasksDone = tasksDone,
    tasksCount = tasksCount
)

internal fun SingleTaskGroupWithTasks.toDto() = SingleTaskGroupWithTasksDto(
    taskGroup = taskGroup.toDto(),
    tasks = tasks.map { it.toDto() }
)

internal fun SingleTaskEntry.toDto() = SingleTaskEntryDto(
    id = id,
    name = name,
    done = done,
    groupId = groupId,
    createdAt = createdAt
)

internal fun TaskWidgetEntry.toDto() = TaskWidgetEntryDto(
    appWidgetId = appWidgetId,
    taskGroupId = taskGroupId,
    widgetColor = widgetColor
)

internal fun SingleTaskGroup.toDto() = SingleTaskGroupDto(
    name = name,
    id = id,
    createdAt = createdAt
)

internal fun TitledTaskWidgetEntry.toDto() = TitledTaskWidgetEntryDto(
    appWidgetId = appWidgetId,
    taskGroupId = taskGroupId,
    widgetColor = widgetColor,
    taskGroupTitle = taskGroupTitle
)
