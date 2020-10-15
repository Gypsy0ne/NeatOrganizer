package one.gypsy.neatorganizer.domain.datasource.tasks

import one.gypsy.neatorganizer.data.database.dao.tasks.TaskWidgetsDao
import one.gypsy.neatorganizer.data.database.entity.tasks.toWidgetTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.dto.tasks.toTaskWidgetEntity

class UserTaskWidgetDataSource(private val widgetsDao: TaskWidgetsDao) :
    TaskWidgetDataSource {
    override suspend fun save(taskWidgetEntry: TaskWidgetEntry) {

        widgetsDao.insert(taskWidgetEntry.toTaskWidgetEntity())
    }

    override suspend fun load(taskWidgetId: Int): TaskWidgetEntry =
        widgetsDao.getWidgetWithTaskGroupById(taskWidgetId).toWidgetTaskEntry()

    override suspend fun delete(taskWidgetId: Int) {
        TODO("Not yet implemented")
    }

}