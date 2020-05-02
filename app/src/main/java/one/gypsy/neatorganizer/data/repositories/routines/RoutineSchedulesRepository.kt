package one.gypsy.neatorganizer.data.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule
import javax.inject.Inject

class RoutineSchedulesRepository @Inject constructor(var dataSource: RoutineSchedulesDataSource) {

    suspend fun addRoutineSchedule(routineSchedule: RoutineSchedule) =
        dataSource.add(routineSchedule)

    suspend fun updateRoutineSchedule(routineSchedule: RoutineSchedule) =
        dataSource.update(routineSchedule)

    suspend fun removeRoutine(routineSchedule: RoutineSchedule) =
        dataSource.remove(routineSchedule)
}