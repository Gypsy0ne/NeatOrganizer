package one.gypsy.neatorganizer.domain.datasource.routines

import one.gypsy.neatorganizer.database.dao.routines.RoutineSchedulesDao
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineScheduleEntity

class UserRoutineSchedulesDataSource(private val routineSchedulesDao: RoutineSchedulesDao) :
    RoutineSchedulesDataSource {

    override suspend fun add(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.insert(routineSchedule.toRoutineScheduleEntity())

    override suspend fun remove(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.delete(routineSchedule.toRoutineScheduleEntity())

    override suspend fun update(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.update(routineSchedule.toRoutineScheduleEntity())
}
