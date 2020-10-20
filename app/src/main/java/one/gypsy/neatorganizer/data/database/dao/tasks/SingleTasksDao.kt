package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity

@Dao
interface SingleTasksDao :
    BaseDao<SingleTaskEntity> {
    @Query("SELECT * FROM single_tasks WHERE groupId = :taskGroupId")
    fun getAllSingleTasksByGroupId(taskGroupId: Long): List<SingleTaskEntity>

    @Query("SELECT * FROM single_tasks WHERE groupId = :taskGroupId")
    fun getAllSingleTasksByGroupIdObservable(taskGroupId: Long): LiveData<List<SingleTaskEntity>>
}