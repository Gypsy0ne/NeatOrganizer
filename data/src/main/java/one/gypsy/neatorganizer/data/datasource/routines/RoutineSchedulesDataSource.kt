package one.gypsy.neatorganizer.data.datasource.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineSchedule

interface RoutineSchedulesDataSource {
    suspend fun add(routineSchedule: RoutineSchedule)
    suspend fun remove(routineSchedule: RoutineSchedule)
    suspend fun update(routineSchedule: RoutineSchedule)
}
