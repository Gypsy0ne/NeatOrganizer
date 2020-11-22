package one.gypsy.neatorganizer.data.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.TaskWidgetDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry

class TaskWidgetsRepository(private val taskWidgetDataSource: TaskWidgetDataSource) {

    suspend fun createTaskWidget(widgetEntry: TaskWidgetEntry) =
        taskWidgetDataSource.createTaskWidget(widgetEntry)

    suspend fun deleteTaskWidgetById(taskWidgetId: Int) =
        taskWidgetDataSource.deleteTaskWidgetById(taskWidgetId)

    suspend fun getTitledTaskWidgetById(taskWidgetId: Int) =
        taskWidgetDataSource.getTitledTaskWidgetById(taskWidgetId)

    suspend fun getTitledTaskWidgetByIdObservable(taskWidgetId: Int) =
        taskWidgetDataSource.getTitledTaskWidgetByIdObservable(taskWidgetId)

    suspend fun getTaskGroupIdByWidgetId(taskWidgetId: Int) =
        taskWidgetDataSource.getTaskGroupIdByWidgetId(taskWidgetId)

    suspend fun updateLinkedTaskGroup(taskWidgetId: Int, taskGroupId: Long) =
        taskWidgetDataSource.updateLinkedTaskGroup(taskWidgetId, taskGroupId)

    suspend fun getAllWidgetIds() = taskWidgetDataSource.getAllWidgetIds()

    suspend fun getAllTaskWidgets() = taskWidgetDataSource.getAllTaskWidgetsObservable()
}