package one.gypsy.neatorganizer.data.datasource.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.data.model.routines.toRoutineTaskEntity
import one.gypsy.neatorganizer.database.dao.routines.RoutineTasksDao

internal class UserRoutineTasksDataSource(private val routineTasksDao: RoutineTasksDao) :
    RoutineTasksDataSource {

    override suspend fun add(routineTask: RoutineTaskEntry) =
        routineTasksDao.insert(routineTask.toRoutineTaskEntity())

    override suspend fun update(routineTask: RoutineTaskEntry) =
        routineTasksDao.update(routineTask.toRoutineTaskEntity())

    override suspend fun remove(routineTask: RoutineTaskEntry) =
        routineTasksDao.delete(routineTask.toRoutineTaskEntity())

    override suspend fun resetTasksStatus() = routineTasksDao.resetTasksStatus()
}
