package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotsWithDaysAndTasks

@Dao
interface RoutineSnapshotsDao : BaseDao<RoutineSnapshotEntity> {

    @Transaction
    @Query("SELECT * FROM routine_snapshots")
    fun getSnapshotsWithDaysAndTasks(): List<RoutineSnapshotsWithDaysAndTasks>

    @Transaction
    @Query("SELECT * FROM routine_snapshots")
    fun getAllRoutineSnapshots(): List<RoutineSnapshotEntity>
}