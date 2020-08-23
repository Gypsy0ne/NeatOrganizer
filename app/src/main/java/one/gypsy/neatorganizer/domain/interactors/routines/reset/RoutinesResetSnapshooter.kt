package one.gypsy.neatorganizer.domain.interactors.routines.reset

import one.gypsy.neatorganizer.data.repositories.routines.RoutinesRepository
import one.gypsy.neatorganizer.data.repositories.routines.reset.RoutineSnapshotsRepository
import one.gypsy.neatorganizer.domain.dto.routines.RoutineTaskEntry
import one.gypsy.neatorganizer.domain.dto.routines.reset.RoutineSnapshot
import java.util.*
import kotlin.collections.LinkedHashMap

//znalezc sposob na kasownaie tego
class RoutinesResetSnapshooter(
    val routinesRepository: RoutinesRepository,
    val routineSnapshotsRepository: RoutineSnapshotsRepository
) {

    suspend fun performWeeklyRoutinesReset() {
        val allRoutines = routinesRepository.getAllRoutines()
        val tasksGroupedByDayId = LinkedHashMap<Int, List<RoutineTaskEntry>>()
        //pogrupowanie wszystkow mape <dzien, lista zadan z niego>
        allRoutines.forEach { routine ->
            routine.schedule.scheduledDays.forEachIndexed { index, scheduled ->
                if (scheduled) {
                    val scheduledDayTasks = tasksGroupedByDayId[index]
                    if (scheduledDayTasks != null) {
                        tasksGroupedByDayId[index] = scheduledDayTasks.plus(routine.tasks)
                    } else {
                        tasksGroupedByDayId[index] = routine.tasks
                    }
                }
            }
        }

        //utworzenie snapshota
        var tasksCount = 0
        allRoutines.forEach { tasksCount += it.tasks.size }
        val resetSnapshot = RoutineSnapshot(tasksCount, Date())

        //wrzucenie snapshota
        val snapshotId = routineSnapshotsRepository.addRoutineSnapshot(resetSnapshot)


    }
}