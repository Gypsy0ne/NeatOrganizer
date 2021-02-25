package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.model.routines.RoutineSchedule

data class RoutineScheduleDto(
    val routineId: Long = 0,
    val scheduledDays: List<Boolean> = List(7) {
        false
    }
) {
    companion object {
        val EMPTY = RoutineScheduleDto()
    }
}

internal fun RoutineScheduleDto.toRoutineSchedule() = RoutineSchedule(
    routineId = this.routineId,
    scheduledDays = scheduledDays
)
