package one.gypsy.neatorganizer.domain.datasource.routines

import one.gypsy.neatorganizer.data.database.dao.routines.RoutineTasksDao
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineTaskEntity

class UserRoutineTasksDataSource(val routineTasksDao: RoutineTasksDao) :
    RoutineTasksDataSource {

    override suspend fun add(routineTask: RoutineTaskEntry): Long =
        routineTasksDao.insert(routineTask.toRoutineTaskEntity())

    override suspend fun update(routineTask: RoutineTaskEntry) =
        routineTasksDao.update(routineTask.toRoutineTaskEntity())

    override suspend fun remove(routineTask: RoutineTaskEntry) =
        routineTasksDao.delete(routineTask.toRoutineTaskEntity())

    override suspend fun resetTasksStatus() = routineTasksDao.resetTasksStatus()

}