package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity

class TaskWidgetEntry(
    val appWidgetId: Int,
    val taskGroupId: Long,
    val widgetColor: Int
)

fun TaskWidgetEntry.toTaskWidgetEntity() = TaskWidgetEntity(
    widgetId = appWidgetId,
    taskGroupId = taskGroupId,
    color = widgetColor
)
