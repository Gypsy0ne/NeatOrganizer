package one.gypsy.neatorganizer.domain.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineTasksDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntryDto

class RoutineTasksRepository(private val dataSource: RoutineTasksDataSource) {

    suspend fun addRoutineTask(routineTaskEntry: RoutineTaskEntryDto) =
        dataSource.add(routineTaskEntry)

    suspend fun updateRoutineTask(routineTaskEntry: RoutineTaskEntryDto) =
        dataSource.update(routineTaskEntry)

    suspend fun removeRoutineTask(routineTaskEntry: RoutineTaskEntryDto) =
        dataSource.remove(routineTaskEntry)

    suspend fun resetAllRoutineTasks() = dataSource.resetTasksStatus()
}
