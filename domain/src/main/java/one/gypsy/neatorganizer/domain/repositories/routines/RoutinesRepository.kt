package one.gypsy.neatorganizer.domain.repositories.routines

import androidx.lifecycle.map
import one.gypsy.neatorganizer.data.datasource.routines.RoutinesDataSource
import one.gypsy.neatorganizer.domain.dto.routines.RoutineWithTasksDto
import one.gypsy.neatorganizer.domain.dto.routines.toDto
import one.gypsy.neatorganizer.domain.dto.routines.toRoutineWithTasks

class RoutinesRepository(private val dataSource: RoutinesDataSource) {

    suspend fun addRoutine(routine: RoutineWithTasksDto) =
        dataSource.add(routine.toRoutineWithTasks())

    suspend fun updateRoutine(routine: RoutineWithTasksDto) =
        dataSource.update(routine.toRoutineWithTasks())

    suspend fun removeRoutine(routine: RoutineWithTasksDto) =
        dataSource.remove(routine.toRoutineWithTasks())

    suspend fun getAllRoutinesObservable() = dataSource.getAllRoutinesObservable().map { routines ->
        routines.map { it.toDto() }
    }

    suspend fun getAllRoutines() = dataSource.getAllRoutines()

    suspend fun removeRoutineById(routineId: Long) = dataSource.removeRoutineById(routineId)
}
