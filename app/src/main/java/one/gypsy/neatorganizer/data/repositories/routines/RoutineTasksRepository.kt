package one.gypsy.neatorganizer.data.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineTasksDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import javax.inject.Inject

class RoutineTasksRepository @Inject constructor(var dataSource: RoutineTasksDataSource) {

    suspend fun addRoutine(routineTaskEntry: RoutineTaskEntry) = dataSource.add(routineTaskEntry)

    suspend fun removeRoutine(routineTaskEntry: RoutineTaskEntry) =
        dataSource.remove(routineTaskEntry)
}