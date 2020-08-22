package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.data.database.entity.routines.RoutineSnapshotDayWithTasks

interface RoutineSnapshotDaysDao {

    @Transaction
    @Query("SELECT * FROM routine_snapshot_days")
    fun getSnapshotDaysWithTasks(): List<RoutineSnapshotDayWithTasks>
}