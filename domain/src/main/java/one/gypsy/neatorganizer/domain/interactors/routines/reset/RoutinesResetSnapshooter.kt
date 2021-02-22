package one.gypsy.neatorganizer.domain.interactors.routines.reset

import one.gypsy.neatorganizer.data.model.routines.RoutineWithTasks
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshotDto
import one.gypsy.neatorganizer.domain.repositories.routines.RoutineTasksRepository
import one.gypsy.neatorganizer.domain.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.domain.repositories.routines.reset.RoutineSnapshotsRepository
import java.util.Date

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

    private fun createRoutinesResetSnapshot(allRoutinesWithTasks: List<RoutineWithTasks>): RoutineSnapshotDto {
        val allTasks = allRoutinesWithTasks.flatMap { it.tasks }
        val tasksDone = allTasks.filter { it.done }.size
        return RoutineSnapshotDto(allTasks.size, tasksDone, Date())
    }
}
