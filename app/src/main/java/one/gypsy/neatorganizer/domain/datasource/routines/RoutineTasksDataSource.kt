package one.gypsy.neatorganizer.domain.datasource.routines

import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry

interface RoutineTasksDataSource {

    suspend fun add(routineTask: RoutineTaskEntry): Long
    suspend fun update(routineTask: RoutineTaskEntry)
    suspend fun remove(routineTask: RoutineTaskEntry)
    suspend fun resetTasksStatus()
}