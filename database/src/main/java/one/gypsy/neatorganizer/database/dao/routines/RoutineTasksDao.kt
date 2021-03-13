package one.gypsy.neatorganizer.database.dao.routines

import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.database.dao.BaseDao
import one.gypsy.neatorganizer.database.entity.routines.RoutineTaskEntity

@Dao
interface RoutineTasksDao :
    BaseDao<RoutineTaskEntity> {

    @Query("UPDATE routine_tasks SET done = 0")
    fun resetTasksStatus()

    @Query("SELECT * FROM routine_tasks")
    fun getAllRoutineTasks(): List<RoutineTaskEntity>
}
