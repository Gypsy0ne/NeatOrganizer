package one.gypsy.neatorganizer.domain.interactors.routines.reset

import one.gypsy.neatorganizer.data.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotsRepository
import one.gypsy.neatorganizer.domain.dto.routines.Routine
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshot
import java.util.*

class RoutinesResetSnapshooter(
    private val routinesRepository: RoutinesRepository,
    private val routineSnapshotsRepository: RoutineSnapshotsRepository,
    private val routineTasksRepository: RoutineTasksRepository
) {

    suspend fun performWeeklyRoutinesReset() {
        val snapshot = createRoutinesResetSnapshot(routinesRepository.getAllRoutines())
        routineSnapshotsRepository.addRoutineSnapshot(snapshot)
        routineTasksRepository.resetAllRoutineTasks()
    }

    private fun createRoutinesResetSnapshot(allRoutines: List<Routine>): RoutineSnapshot {
        val allTasks = allRoutines.flatMap { it.tasks }
        val tasksDone = allTasks.filter { it.done }.size
        return RoutineSnapshot(allTasks.size, tasksDone, Date())
    }
}