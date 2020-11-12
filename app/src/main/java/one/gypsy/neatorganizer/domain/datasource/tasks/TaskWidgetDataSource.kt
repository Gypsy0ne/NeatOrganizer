package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry

interface TaskWidgetDataSource {
    suspend fun save(taskWidgetEntry: TitledTaskWidgetEntry)
    suspend fun getTitledTaskWidgetById(taskWidgetId: Int): TitledTaskWidgetEntry
    suspend fun getTitledTaskWidgetByIdObservable(taskWidgetId: Int): LiveData<TitledTaskWidgetEntry>
    suspend fun getTaskGroupIdByWidgetId(taskWidgetId: Int): Long
    suspend fun delete(taskWidgetId: Int)
    suspend fun updateLinkedTaskGroup(taskWidgetId: Int, taskGroupId: Long)
    suspend fun getAllWidgetIds(): IntArray
    suspend fun getAllTaskWidgetsObservable(): LiveData<List<TaskWidgetEntry>>
}