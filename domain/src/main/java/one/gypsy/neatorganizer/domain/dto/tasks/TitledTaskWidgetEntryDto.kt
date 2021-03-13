package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.TitledTaskWidgetEntry

data class TitledTaskWidgetEntryDto(
    val appWidgetId: Int,
    val taskGroupId: Long,
    val widgetColor: Int,
    val taskGroupTitle: String
)

internal fun TitledTaskWidgetEntryDto.toTitledTaskWidgetEntry() = TitledTaskWidgetEntry(
    appWidgetId = appWidgetId,
    taskGroupId = taskGroupId,
    taskGroupTitle = taskGroupTitle,
    widgetColor = widgetColor
)
