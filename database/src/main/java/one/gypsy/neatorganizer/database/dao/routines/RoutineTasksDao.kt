package one.gypsy.neatorganizer.database.dao.routines

import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.database.dao.BaseDao
import one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity

@Dao
interface RoutineTasksDao : BaseDao<RoutineTaskEntity> {

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.monday = 1)")
    fun resetMondayTasksProgress()

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.tuesday = 1)")
    fun resetTuesdayTasksProgress()

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.wednesday = 1)")
    fun resetWednesdayTasksProgress()

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.thursday = 1)")
    fun resetThursdayTasksProgress()

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.friday = 1)")
    fun resetFridayTasksProgress()

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.saturday = 1)")
    fun resetSaturdayTasksProgress()

    @Query("UPDATE routine_tasks SET done = 0 WHERE EXISTS(SELECT * FROM routine_schedules WHERE routine_schedules.routineId = routine_tasks.routineId AND routine_schedules.sunday = 1)")
    fun resetSundayTasksProgress()

    @Query("SELECT * FROM routine_tasks")
    fun getAllRoutineTasks(): List<RoutineTaskEntity>
}
