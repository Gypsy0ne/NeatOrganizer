package one.gypsy.neatorganizer.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineSchedule

class RoutineSchedulesRepository(private val dataSource: RoutineSchedulesDataSource) {

    suspend fun addRoutineSchedule(routineSchedule: RoutineSchedule) =
        dataSource.add(routineSchedule)

    suspend fun updateRoutineSchedule(routineSchedule: RoutineSchedule) =
        dataSource.update(routineSchedule)

    suspend fun removeRoutine(routineSchedule: RoutineSchedule) =
        dataSource.remove(routineSchedule)
}
