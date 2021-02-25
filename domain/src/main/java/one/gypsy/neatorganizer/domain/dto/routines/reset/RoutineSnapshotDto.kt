package one.gypsy.neatorganizer.domain.dto.routines.reset

import one.gypsy.neatorganizer.data.model.routines.reset.RoutineSnapshot
import java.util.Date

data class RoutineSnapshotDto(
    val tasksOverall: Int,
    val tasksDone: Int,
    val resetDate: Date,
    val id: Long = 0
)

internal fun RoutineSnapshotDto.toRoutineSnapshot() = RoutineSnapshot(
    tasksOverall,
    tasksDone,
    resetDate
)
