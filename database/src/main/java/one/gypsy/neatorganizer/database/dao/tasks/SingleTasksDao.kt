package one.gypsy.neatorganizer.database.dao.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.database.dao.BaseDao
import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity

@Dao
interface SingleTasksDao : BaseDao<SingleTaskEntity> {

    @Query("SELECT * FROM single_tasks WHERE groupId = :taskGroupId")
    fun getAllSingleTasksByGroupId(taskGroupId: Long): List<SingleTaskEntity>

    @Query("SELECT * FROM single_tasks WHERE groupId = :taskGroupId")
    fun getAllSingleTasksByGroupIdObservable(taskGroupId: Long): LiveData<List<SingleTaskEntity>>
}
