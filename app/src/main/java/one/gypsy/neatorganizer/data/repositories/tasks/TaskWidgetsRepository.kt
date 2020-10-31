package one.gypsy.neatorganizer.data.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.TaskWidgetDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry

class TaskWidgetsRepository(private val taskWidgetDataSource: TaskWidgetDataSource) {
    suspend fun create(widgetEntry: TaskWidgetEntry) = taskWidgetDataSource.save(widgetEntry)
    suspend fun delete(taskWidgetId: Int) = taskWidgetDataSource.delete(taskWidgetId)
    suspend fun load(taskWidgetId: Int) = taskWidgetDataSource.load(taskWidgetId)
    suspend fun getAllWidgetIds() = taskWidgetDataSource.getAllWidgetIds()
}