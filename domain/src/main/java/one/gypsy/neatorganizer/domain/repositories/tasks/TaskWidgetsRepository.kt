package one.gypsy.neatorganizer.domain.repositories.tasks

import androidx.lifecycle.map
import one.gypsy.neatorganizer.data.datasource.tasks.TaskWidgetDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntryDto
import one.gypsy.neatorganizer.domain.dto.tasks.toDto
import one.gypsy.neatorganizer.domain.dto.tasks.toTaskWidgetEntry

class TaskWidgetsRepository(private val taskWidgetDataSource: TaskWidgetDataSource) {

    suspend fun createTaskWidget(widgetEntry: TaskWidgetEntryDto) =
        taskWidgetDataSource.createTaskWidget(widgetEntry.toTaskWidgetEntry())

    suspend fun deleteTaskWidgetById(taskWidgetId: Int) =
        taskWidgetDataSource.deleteTaskWidgetById(taskWidgetId)

    suspend fun getTitledTaskWidgetById(taskWidgetId: Int) =
        taskWidgetDataSource.getTitledTaskWidgetById(taskWidgetId).toDto()

    suspend fun getTitledTaskWidgetByIdObservable(taskWidgetId: Int) =
        taskWidgetDataSource.getTitledTaskWidgetByIdObservable(taskWidgetId).map { it.toDto() }

    suspend fun getTaskGroupIdByWidgetId(taskWidgetId: Int) =
        taskWidgetDataSource.getTaskGroupIdByWidgetId(taskWidgetId)

    suspend fun updateLinkedTaskGroup(taskWidgetId: Int, taskGroupId: Long) =
        taskWidgetDataSource.updateLinkedTaskGroup(taskWidgetId, taskGroupId)

    suspend fun getAllWidgetIds() = taskWidgetDataSource.getAllWidgetIds()

    suspend fun getAllTaskWidgets() =
        taskWidgetDataSource.getAllTaskWidgetsObservable().map { widgets ->
            widgets.map { it.toDto() }
        }
}
