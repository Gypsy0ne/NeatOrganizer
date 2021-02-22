package one.gypsy.neatorganizer.domain.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineScheduleDto

class RoutineSchedulesRepository(private val dataSource: RoutineSchedulesDataSource) {

    suspend fun addRoutineSchedule(routineSchedule: RoutineScheduleDto) =
        dataSource.add(routineSchedule)

    suspend fun updateRoutineSchedule(routineSchedule: RoutineScheduleDto) =
        dataSource.update(routineSchedule)

    suspend fun removeRoutine(routineSchedule: RoutineScheduleDto) =
        dataSource.remove(routineSchedule)
}
