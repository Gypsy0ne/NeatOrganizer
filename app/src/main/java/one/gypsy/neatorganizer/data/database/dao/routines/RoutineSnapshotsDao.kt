package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineSnapshotsWithDaysAndTasks

interface RoutineSnapshotsDao {

    @Transaction
    @Query("SELECT * FROM routine_snapshots")
    fun getSnapshotsWithDaysAndTasks(): List<RoutineSnapshotsWithDaysAndTasks>
}