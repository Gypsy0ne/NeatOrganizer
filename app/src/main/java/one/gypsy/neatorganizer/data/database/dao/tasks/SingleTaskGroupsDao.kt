package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.GroupWithSingleTasks
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity

@Dao
interface SingleTaskGroupsDao :
    BaseDao<SingleTaskGroupEntity> {

    @Transaction
    @Query("SELECT * FROM single_task_group")
    fun getAllGroupsWithSingleTasks(): LiveData<List<GroupWithSingleTasks>>

    @Query("SELECT * FROM single_task_group")
    fun getAllSingleTaskGroups(): LiveData<List<SingleTaskGroupEntity>>

    @Query("SELECT * FROM single_task_group WHERE id = :taskGroupId")
    fun getSingleTaskGroupById(taskGroupId: Long): LiveData<SingleTaskGroupEntity>

    @Query("SELECT * FROM single_task_group WHERE id = :taskGroupId")
    fun getGroupWithSingleTasksById(taskGroupId: Long): LiveData<GroupWithSingleTasks>

    @Query("DELETE FROM SINGLE_TASK_GROUP WHERE id = :taskGroupId")
    fun deleteTaskGroupById(taskGroupId: Long)


}