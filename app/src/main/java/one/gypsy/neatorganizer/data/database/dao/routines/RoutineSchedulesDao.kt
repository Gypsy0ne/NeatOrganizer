package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineScheduleEntity

@Dao
interface RoutineSchedulesDao : BaseDao<RoutineScheduleEntity> {
    @Query("SELECT * FROM routine_schedules")
    fun getAllRoutineSchedules(): List<RoutineScheduleEntity>
}