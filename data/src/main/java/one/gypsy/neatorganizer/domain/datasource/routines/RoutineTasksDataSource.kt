package one.gypsy.neatorganizer.domain.datasource.routines

import one.gypsy.neatorganizer.domain.routines.RoutineTaskEntry

interface RoutineTasksDataSource {
    suspend fun add(routineTask: RoutineTaskEntry)
    suspend fun update(routineTask: RoutineTaskEntry)
    suspend fun remove(routineTask: RoutineTaskEntry)
    suspend fun resetTasksStatus()
}
