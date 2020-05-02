package one.gypsy.neatorganizer.domain.datasource.routines

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.routines.RoutinesDao
import one.gypsy.neatorganizer.data.database.entity.routines.toRoutine
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineEntity
import javax.inject.Inject

class UserRoutinesDataSource @Inject constructor(val routinesDao: RoutinesDao) :
    RoutinesDataSource {

    override suspend fun add(routine: Routine): Long = routinesDao.insert(routine.toRoutineEntity())

    override suspend fun remove(routine: Routine) = routinesDao.delete(routine.toRoutineEntity())

    override suspend fun update(routine: Routine) = routinesDao.update(routine.toRoutineEntity())

    override suspend fun getAllRoutines(): LiveData<List<Routine>> =
        Transformations.map(routinesDao.getAllScheduledRoutinesWithTasks()) { scheduledRoutinesWithTasks ->
            scheduledRoutinesWithTasks.map {
                it.toRoutine()
            }
        }
}