package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.TaskWidgetEntry

class TaskWidgetEntryDto(
    val appWidgetId: Int,
    val taskGroupId: Long,
    val widgetColor: Int
)

internal fun TaskWidgetEntryDto.toTaskWidgetEntry() = TaskWidgetEntry(
    appWidgetId = appWidgetId,
    taskGroupId = taskGroupId,
    widgetColor = widgetColor
)
