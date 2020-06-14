package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.lifecycle.LiveData
import androidx.room.*
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineEntity
import one.gypsy.neatorganizer.data.database.entity.routines.ScheduledRoutineWithTasks

@Dao
interface RoutinesDao : BaseDao<RoutineEntity> {

    @Transaction
    @Query("SELECT * FROM routines")
    fun getAllScheduledRoutinesWithTasks(): LiveData<List<ScheduledRoutineWithTasks>>
}