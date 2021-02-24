package one.gypsy.neatorganizer.domain.repositories.routines

import one.gypsy.neatorganizer.data.datasource.routines.RoutineSchedulesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineScheduleDto
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineSchedule

class RoutineSchedulesRepository(private val dataSource: RoutineSchedulesDataSource) {

    suspend fun addRoutineSchedule(routineSchedule: RoutineScheduleDto) =
        dataSource.add(routineSchedule.toRoutineSchedule())

    suspend fun updateRoutineSchedule(routineSchedule: RoutineScheduleDto) =
        dataSource.update(routineSchedule.toRoutineSchedule())

    suspend fun removeRoutine(routineSchedule: RoutineScheduleDto) =
        dataSource.remove(routineSchedule.toRoutineSchedule())
}
