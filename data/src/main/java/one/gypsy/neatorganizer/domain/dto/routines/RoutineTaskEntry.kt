package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity

data class RoutineTaskEntry(
    val id: Long = 0,
    val routineId: Long,
    val name: String,
    var done: Boolean,
    val createdAt: Long
)

fun RoutineTaskEntry.toRoutineTaskEntity() = RoutineTaskEntity(
    name = this.name,
    done = this.done,
    routineId = this.routineId,
    id = this.id,
    createdAt = this.createdAt
)
