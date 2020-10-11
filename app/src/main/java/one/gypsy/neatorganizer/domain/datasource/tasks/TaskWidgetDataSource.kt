package one.gypsy.neatorganizer.domain.datasource.tasks

import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry

interface TaskWidgetDataSource {
    suspend fun save(taskWidgetEntry: TaskWidgetEntry)
    suspend fun load(taskWidgetId: Int): TaskWidgetEntry
    suspend fun delete(taskWidgetId: Int)
}