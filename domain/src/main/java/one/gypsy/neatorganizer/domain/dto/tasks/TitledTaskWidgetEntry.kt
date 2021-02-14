package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity

data class TitledTaskWidgetEntry(
    val appWidgetId: Int,
    val taskGroupId: Long,
    val widgetColor: Int,
    val taskGroupTitle: String
)

fun TitledTaskWidgetEntry.toTaskWidgetEntity() = TaskWidgetEntity(
    widgetId = appWidgetId,
    taskGroupId = taskGroupId,
    color = widgetColor
)
