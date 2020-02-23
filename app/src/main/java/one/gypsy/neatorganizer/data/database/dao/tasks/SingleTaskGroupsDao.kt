package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.GroupWithSingleTasks

@Dao
interface SingleTaskGroupsDao:
    BaseDao<SingleTaskGroupsDao> {

    @Query("SELECT * FROM single_task_group")
    fun getAllGroupsWithSingleTasks(): LiveData<List<GroupWithSingleTasks>>
}