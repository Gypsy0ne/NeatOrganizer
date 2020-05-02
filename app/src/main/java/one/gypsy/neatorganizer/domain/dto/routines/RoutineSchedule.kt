package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.data.database.entity.routines.RoutineScheduleEntity

data class RoutineSchedule(val id: Long, val routineId: Long, val schedulesDays: List<Boolean>)

fun RoutineSchedule.toRoutineScheduleEntity() = RoutineScheduleEntity(
    id = this.id,
    routineId = this.routineId,
    monday = this.schedulesDays[0],
    tuesday = this.schedulesDays[1],
    wednesday = this.schedulesDays[2],
    thursday = this.schedulesDays[3],
    friday = this.schedulesDays[4],
    saturday = this.schedulesDays[5],
    sunday = this.schedulesDays[6]
)