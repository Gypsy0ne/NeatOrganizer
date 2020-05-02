package one.gypsy.neatorganizer.domain.datasource.routines

import one.gypsy.neatorganizer.data.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineScheduleEntity
import javax.inject.Inject

class UserRoutineSchedulesDataSource @Inject constructor(val routineSchedulesDao: RoutineSchedulesDao) :
    RoutineSchedulesDataSource {

    override suspend fun add(routineSchedule: RoutineSchedule): Long =
        routineSchedulesDao.insert(routineSchedule.toRoutineScheduleEntity())

    override suspend fun remove(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.delete(routineSchedule.toRoutineScheduleEntity())

    override suspend fun update(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.update(routineSchedule.toRoutineScheduleEntity())
}