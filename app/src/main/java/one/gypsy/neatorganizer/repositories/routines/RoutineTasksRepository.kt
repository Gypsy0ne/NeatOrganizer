package one.gypsy.neatorganizer.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineTasksDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry

class RoutineTasksRepository(private val dataSource: RoutineTasksDataSource) {

    suspend fun addRoutineTask(routineTaskEntry: RoutineTaskEntry) =
        dataSource.add(routineTaskEntry)

    suspend fun updateRoutineTask(routineTaskEntry: RoutineTaskEntry) =
        dataSource.update(routineTaskEntry)

    suspend fun removeRoutineTask(routineTaskEntry: RoutineTaskEntry) =
        dataSource.remove(routineTaskEntry)

    suspend fun resetAllRoutineTasks() = dataSource.resetTasksStatus()
}
