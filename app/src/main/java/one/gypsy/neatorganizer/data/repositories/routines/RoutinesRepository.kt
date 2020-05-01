package one.gypsy.neatorganizer.data.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutinesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import javax.inject.Inject

class RoutinesRepository @Inject constructor(var dataSource: RoutinesDataSource) {

    suspend fun addRoutine(routine: Routine) = dataSource.add(routine)

    suspend fun removeRoutine(routine: Routine) = dataSource.remove(routine)

    suspend fun getAllRoutines() = dataSource.getAllRoutines()
}