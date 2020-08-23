package one.gypsy.neatorganizer.domain.dto.routines.reset

import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotDayEntity
import java.util.*

data class RoutineSnapshotDay(
    val snapshotId: Long,
    val dayId: Int,
    val snapshotDate: Date,
    val id: Long = 0
)

fun RoutineSnapshotDay.toRoutineSnapshotDayEntity() =
    RoutineSnapshotDayEntity(snapshotId, dayId, snapshotDate)