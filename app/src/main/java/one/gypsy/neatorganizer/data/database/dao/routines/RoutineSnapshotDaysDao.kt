package one.gypsy.neatorganizer.data.database.dao.routines

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotDayEntity
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotDayWithTasks
import one.gypsy.neatorganizer.data.database.entity.routines.reset.RoutineSnapshotTaskEntity

@Dao
abstract class RoutineSnapshotDaysDao : BaseDao<RoutineSnapshotDayEntity> {

    @Transaction
    @Query("SELECT * FROM routine_snapshot_days")
    abstract fun getSnapshotDaysWithTasks(): List<RoutineSnapshotDayWithTasks>

    @Insert
    fun addDayWithTasks(
        snapshotDay: RoutineSnapshotDayEntity,
        snapshotTasks: List<RoutineSnapshotTaskEntity>
    ) {
        val snapshotDayId = insert(snapshotDay)

    }
}