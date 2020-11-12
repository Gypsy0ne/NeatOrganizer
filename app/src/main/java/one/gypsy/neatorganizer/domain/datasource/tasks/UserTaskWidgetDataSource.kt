package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import one.gypsy.neatorganizer.data.database.entity.tasks.toTaskWidgetEntry
import one.gypsy.neatorganizer.data.database.entity.tasks.toTitledWidgetTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry
import one.gypsy.neatorganizer.domain.dto.tasks.toTaskWidgetEntity

class UserTaskWidgetDataSource(private val widgetsDao: TaskWidgetsDao) :
    TaskWidgetDataSource {
    override suspend fun save(titledTaskWidgetEntry: TitledTaskWidgetEntry) {
        widgetsDao.insert(titledTaskWidgetEntry.toTaskWidgetEntity())
    }

    override suspend fun getTitledTaskWidgetById(taskWidgetId: Int): TitledTaskWidgetEntry =
        widgetsDao.getWidgetWithTaskGroupById(taskWidgetId).toTitledWidgetTaskEntry()

    override suspend fun getTitledTaskWidgetByIdObservable(taskWidgetId: Int): LiveData<TitledTaskWidgetEntry> =
        Transformations.map(widgetsDao.getWidgetWithTaskGroupByIdObservable(taskWidgetId)) {
            it.toTitledWidgetTaskEntry()
        }

    override suspend fun getTaskGroupIdByWidgetId(taskWidgetId: Int): Long =
        widgetsDao.getTaskGroupIdByWidgetId(taskWidgetId)

    override suspend fun delete(taskWidgetId: Int) = widgetsDao.deleteWidgetById(taskWidgetId)

    override suspend fun updateLinkedTaskGroup(taskWidgetId: Int, taskGroupId: Long) =
        widgetsDao.updateLinkedTaskGroupById(taskWidgetId, taskGroupId)

    override suspend fun getAllWidgetIds() = widgetsDao.getAllWidgetIds()

    override suspend fun getAllTaskWidgetsObservable() =
        Transformations.map(widgetsDao.getAllTaskWidgetsObservable()) {
            it.map { taskWidgetEntity -> taskWidgetEntity.toTaskWidgetEntry() }
        }

}