package one.gypsy.neatorganizer.data.datasource.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineTaskEntry

interface RoutineTasksDataSource {
    suspend fun add(routineTask: RoutineTaskEntry)
    suspend fun update(routineTask: RoutineTaskEntry)
    suspend fun remove(routineTask: RoutineTaskEntry)
    suspend fun resetTasksStatus()
}
