package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotTaskEntity

@Dao
interface RoutineSnapshotTasksDao : BaseDao<RoutineSnapshotTaskEntity> {
    @Transaction
    @Query("SELECT * FROM routine_snapshot_tasks")
    fun getAllRoutineSnapshotTasks(): List<RoutineSnapshotTaskEntity>
}