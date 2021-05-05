package one.gypsy.neatorganizer.database.dao.routines

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.database.dao.BaseDao
import one.gypsy.neatorganizer.database.entity.routines.reset.RoutineSnapshotEntity

@Dao
interface RoutineSnapshotsDao : BaseDao<RoutineSnapshotEntity> {
    @Transaction
    @Query("SELECT * FROM routine_snapshots")
    fun getAllRoutineSnapshots(): List<RoutineSnapshotEntity>

    @Query("SELECT * FROM routine_snapshots ORDER BY routineSnapshotId DESC LIMIT 1")
    fun getLastResetEntry(): RoutineSnapshotEntity?
}
