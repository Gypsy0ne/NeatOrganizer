package one.gypsy.neatorganizer.data.datasource.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineSchedule
import one.gypsy.neatorganizer.data.model.routines.toRoutineScheduleEntity
import one.gypsy.neatorganizer.database.dao.routines.RoutineSchedulesDao

internal class UserRoutineSchedulesDataSource(private val routineSchedulesDao: RoutineSchedulesDao) :
    RoutineSchedulesDataSource {

    override suspend fun add(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.insert(routineSchedule.toRoutineScheduleEntity())

    override suspend fun remove(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.delete(routineSchedule.toRoutineScheduleEntity())

    override suspend fun update(routineSchedule: RoutineSchedule) =
        routineSchedulesDao.update(routineSchedule.toRoutineScheduleEntity())
}
