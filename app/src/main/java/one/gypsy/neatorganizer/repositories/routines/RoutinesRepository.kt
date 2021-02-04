package one.gypsy.neatorganizer.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutinesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasks

class RoutinesRepository(private val dataSource: RoutinesDataSource) {

    suspend fun addRoutine(routine: RoutineWithTasks) = dataSource.add(routine)

    suspend fun updateRoutine(routine: RoutineWithTasks) = dataSource.update(routine)

    suspend fun removeRoutine(routine: RoutineWithTasks) = dataSource.remove(routine)

    suspend fun getAllRoutinesObservable() = dataSource.getAllRoutinesObservable()

    suspend fun getAllRoutines() = dataSource.getAllRoutines()

    suspend fun removeRoutineById(routineId: Long) = dataSource.removeRoutineById(routineId)
}
