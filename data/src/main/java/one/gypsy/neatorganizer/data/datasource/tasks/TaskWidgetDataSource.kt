package one.gypsy.neatorganizer.data.datasource.tasks

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.data.model.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.data.model.tasks.TitledTaskWidgetEntry

interface TaskWidgetDataSource {
    suspend fun createTaskWidget(taskWidgetEntry: TaskWidgetEntry)
    suspend fun getTitledTaskWidgetById(taskWidgetId: Int): TitledTaskWidgetEntry
    suspend fun getTitledTaskWidgetByIdObservable(taskWidgetId: Int): LiveData<TitledTaskWidgetEntry>
    suspend fun getTaskGroupIdByWidgetId(taskWidgetId: Int): Long
    suspend fun deleteTaskWidgetById(taskWidgetId: Int)
    suspend fun updateLinkedTaskGroup(taskWidgetId: Int, taskGroupId: Long)
    suspend fun getAllWidgetIds(): IntArray
    suspend fun getAllTaskWidgetsObservable(): LiveData<List<TaskWidgetEntry>>
}
