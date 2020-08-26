package one.gypsy.neatorganizer.domain.dto.routines.reset

import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotEntity
import java.util.*

data class RoutineSnapshot(
    val tasksOverall: Int,
    val tasksDone: Int,
    val resetDate: Date,
    val id: Long = 0
)

fun RoutineSnapshot.toRoutineSnapshotEntity() =
    RoutineSnapshotEntity(tasksOverall, tasksDone, resetDate)