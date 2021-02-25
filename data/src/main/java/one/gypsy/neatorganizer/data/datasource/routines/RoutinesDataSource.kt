package one.gypsy.neatorganizer.data.datasource.routines

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.data.model.routines.RoutineWithTasks

interface RoutinesDataSource {
    suspend fun add(routine: RoutineWithTasks): Long
    suspend fun remove(routine: RoutineWithTasks)
    suspend fun update(routine: RoutineWithTasks)
    suspend fun getAllRoutinesObservable(): LiveData<List<RoutineWithTasks>>
    suspend fun getAllRoutines(): List<RoutineWithTasks>
    suspend fun removeRoutineById(routineId: Long)
}
