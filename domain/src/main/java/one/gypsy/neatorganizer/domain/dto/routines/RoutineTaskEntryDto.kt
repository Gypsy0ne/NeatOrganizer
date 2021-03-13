package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineTaskEntry

data class RoutineTaskEntryDto(
    val id: Long = 0,
    val routineId: Long,
    val name: String,
    var done: Boolean,
    val createdAt: Long
)

internal fun RoutineTaskEntryDto.toRoutineTaskEntry() = RoutineTaskEntry(
    name = this.name,
    done = this.done,
    routineId = this.routineId,
    id = this.id,
    createdAt = this.createdAt
)
