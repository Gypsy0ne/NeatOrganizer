package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.database.entity.routines.RoutineScheduleEntity

data class RoutineSchedule(
    val id: Long = 0,
    val routineId: Long = 0,
    val scheduledDays: List<Boolean> = List(7) {
        false
    }
)

fun RoutineSchedule.toRoutineScheduleEntity() = RoutineScheduleEntity(
    id = this.id,
    routineId = this.routineId,
    monday = this.scheduledDays[0],
    tuesday = this.scheduledDays[1],
    wednesday = this.scheduledDays[2],
    thursday = this.scheduledDays[3],
    friday = this.scheduledDays[4],
    saturday = this.scheduledDays[5],
    sunday = this.scheduledDays[6]
)