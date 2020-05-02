package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.database.entity.routines.RoutineEntity

data class Routine(
    val id: Long = 0,
    val name: String,
    val schedule: RoutineSchedule,
    val tasks: List<RoutineTaskEntry> = emptyList()
)

fun Routine.toRoutineEntity() = RoutineEntity(name = this.name, id = this.id)