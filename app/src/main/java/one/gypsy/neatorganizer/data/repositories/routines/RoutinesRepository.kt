package one.gypsy.neatorganizer.data.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutinesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.Routine

class RoutinesRepository(var dataSource: RoutinesDataSource) {

    suspend fun addRoutine(routine: Routine) = dataSource.add(routine)

    suspend fun updateRoutine(routine: Routine) = dataSource.update(routine)

    suspend fun removeRoutine(routine: Routine) = dataSource.remove(routine)

    suspend fun getAllRoutines() = dataSource.getAllRoutines()

    suspend fun removeRoutineById(routineId: Long) = dataSource.removeRoutineById(routineId)
}