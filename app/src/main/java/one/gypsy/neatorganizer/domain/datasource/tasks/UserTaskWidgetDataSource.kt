package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.database.dao.tasks.TaskWidgetsDao
import one.gypsy.neatorganizer.database.entity.tasks.toTaskWidgetEntry
import one.gypsy.neatorganizer.database.entity.tasks.toTitledTaskWidgetEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry
import one.gypsy.neatorganizer.domain.dto.tasks.toTaskWidgetEntity

class UserTaskWidgetDataSource(private val widgetsDao: TaskWidgetsDao) :
    TaskWidgetDataSource {

    override suspend fun createTaskWidget(taskWidgetEntry: TaskWidgetEntry) {
        widgetsDao.insert(taskWidgetEntry.toTaskWidgetEntity())
    }

    override suspend fun getTitledTaskWidgetById(taskWidgetId: Int): TitledTaskWidgetEntry =
        widgetsDao.getWidgetWithTaskGroupById(taskWidgetId).toTitledTaskWidgetEntry()

    override suspend fun getTitledTaskWidgetByIdObservable(taskWidgetId: Int): LiveData<TitledTaskWidgetEntry> =
        Transformations.map(widgetsDao.getWidgetWithTaskGroupByIdObservable(taskWidgetId)) {
            it.toTitledTaskWidgetEntry()
        }

    override suspend fun getTaskGroupIdByWidgetId(taskWidgetId: Int): Long =
        widgetsDao.getTaskGroupIdByWidgetId(taskWidgetId)

    override suspend fun deleteTaskWidgetById(taskWidgetId: Int) =
        widgetsDao.deleteWidgetById(taskWidgetId)

    override suspend fun updateLinkedTaskGroup(taskWidgetId: Int, taskGroupId: Long) =
        widgetsDao.updateLinkedTaskGroupById(taskWidgetId, taskGroupId)

    override suspend fun getAllWidgetIds() = widgetsDao.getAllWidgetIds()

    override suspend fun getAllTaskWidgetsObservable() =
        Transformations.map(widgetsDao.getAllTaskWidgetsObservable()) {
            it.map { taskWidgetEntity -> taskWidgetEntity.toTaskWidgetEntry() }
        }
}
