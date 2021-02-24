package one.gypsy.neatorganizer.data.model.tasks

import one.gypsy.neatorganizer.database.entity.tasks.TaskWidgetEntity

data class TitledTaskWidgetEntry(
    val appWidgetId: Int,
    val taskGroupId: Long,
    val widgetColor: Int,
    val taskGroupTitle: String
)

internal fun TitledTaskWidgetEntry.toTaskWidgetEntity() = TaskWidgetEntity(
    widgetId = appWidgetId,
    taskGroupId = taskGroupId,
    color = widgetColor
)
