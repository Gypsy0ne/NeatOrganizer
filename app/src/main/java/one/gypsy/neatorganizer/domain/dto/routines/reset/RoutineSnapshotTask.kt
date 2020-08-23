package one.gypsy.neatorganizer.domain.dto.routines.reset

import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotTaskEntity

data class RoutineSnapshotTask(val done: Boolean, val taskName: String, var id: Long = 0)

fun RoutineSnapshotTask.toRoutineSnapshotTaskEntity() = RoutineSnapshotTaskEntity(done, taskName)