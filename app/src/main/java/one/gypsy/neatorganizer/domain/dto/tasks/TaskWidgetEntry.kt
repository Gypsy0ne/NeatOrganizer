package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.database.entity.tasks.TaskWidgetEntity

data class TaskWidgetEntry(
    val appWidgetId: Int,
    val taskGroupId: Long,
    val widgetColor: Int,
    val taskGroupTitle: String
)

fun TaskWidgetEntry.toTaskWidgetEntity() =
    TaskWidgetEntity(widgetId = appWidgetId, taskGroupId = taskGroupId, color = widgetColor)