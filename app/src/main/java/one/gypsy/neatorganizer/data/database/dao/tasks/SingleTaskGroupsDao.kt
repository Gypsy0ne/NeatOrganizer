package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.GroupWithSingleTasks
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity

@Dao
interface SingleTaskGroupsDao:
    BaseDao<SingleTaskGroupEntity> {

    @Query("SELECT * FROM single_task_group")
    fun getAllGroupsWithSingleTasks(): LiveData<List<GroupWithSingleTasks>>
}