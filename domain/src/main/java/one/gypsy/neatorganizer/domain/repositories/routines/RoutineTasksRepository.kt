package one.gypsy.neatorganizer.domain.repositories.routines

import one.gypsy.neatorganizer.data.datasource.routines.RoutineTasksDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntryDto
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineTaskEntry

class RoutineTasksRepository(private val dataSource: RoutineTasksDataSource) {

    suspend fun addRoutineTask(routineTaskEntry: RoutineTaskEntryDto) =
        dataSource.add(routineTaskEntry.toRoutineTaskEntry())

    suspend fun updateRoutineTask(routineTaskEntry: RoutineTaskEntryDto) =
        dataSource.update(routineTaskEntry.toRoutineTaskEntry())

    suspend fun removeRoutineTask(routineTaskEntry: RoutineTaskEntryDto) =
        dataSource.remove(routineTaskEntry.toRoutineTaskEntry())

    suspend fun resetMondayTasks() = dataSource.resetMondayTasks()

    suspend fun resetTuesdayTasks() = dataSource.resetTuesdayTasks()

    suspend fun resetWednesdayTasks() = dataSource.resetWednesdayTasks()

    suspend fun resetThursdayTasks() = dataSource.resetThursdayTasks()

    suspend fun resetFridayTasks() = dataSource.resetFridayTasks()

    suspend fun resetSaturdayTasks() = dataSource.resetSaturdayTasks()

    suspend fun resetSundayTasks() = dataSource.resetSundayTasks()
}
