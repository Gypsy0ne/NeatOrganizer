package one.gypsy.neatorganizer.domain.datasource.routines

import one.gypsy.neatorganizer.domain.routines.RoutineSchedule

interface RoutineSchedulesDataSource {
    suspend fun add(routineSchedule: RoutineSchedule)
    suspend fun remove(routineSchedule: RoutineSchedule)
    suspend fun update(routineSchedule: RoutineSchedule)
}
