package one.gypsy.neatorganizer.data.model.routines.reset

import one.gypsy.neatorganizer.database.entity.routines.reset.RoutineSnapshotEntity
import java.util.Date

data class RoutineSnapshot(
    val tasksOverall: Int,
    val tasksDone: Int,
    val resetDate: Date,
    val id: Long = 0
)

internal fun RoutineSnapshot.toRoutineSnapshotEntity() = RoutineSnapshotEntity(
    tasksOverall,
    tasksDone,
    resetDate
)
