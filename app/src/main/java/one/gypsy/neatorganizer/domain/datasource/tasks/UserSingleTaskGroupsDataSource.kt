package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.entity.tasks.toSingleTaskGroup
import one.gypsy.neatorganizer.data.database.entity.tasks.toSingleTaskGroupEntry
import one.gypsy.neatorganizer.data.database.entity.tasks.toSingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskGroupEntity

class UserSingleTaskGroupsDataSource(private val singleTaskGroupsDao: SingleTaskGroupsDao) :
    SingleTaskGroupsDataSource {

    override suspend fun addSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        singleTaskGroupsDao.insert(singleTaskGroupWithTasks.toSingleTaskGroupEntity())

    override suspend fun removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        singleTaskGroupsDao.delete(singleTaskGroupWithTasks.toSingleTaskGroupEntity())

    override suspend fun removeById(taskGroupId: Long) =
        singleTaskGroupsDao.deleteTaskGroupById(taskGroupId)

    override suspend fun updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        singleTaskGroupsDao.update(singleTaskGroupWithTasks.toSingleTaskGroupEntity())

    override suspend fun updateSingleTaskGroup(singleTaskGroup: SingleTaskGroup) {
        singleTaskGroupsDao.update(singleTaskGroup.toSingleTaskGroupEntity())
    }

    override suspend fun getAllSingleTaskGroupsWithTasks(): LiveData<List<SingleTaskGroupWithTasks>> =
        Transformations.map(singleTaskGroupsDao.getAllGroupsWithSingleTasks()) { taskGroups ->
            taskGroups.map {
                it.toSingleTaskGroupWithTasks()
            }
        }

    override suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long): LiveData<SingleTaskGroupWithTasks> =
        Transformations.map(singleTaskGroupsDao.getGroupWithSingleTasksById(taskGroupId)) {
            it.toSingleTaskGroupWithTasks()
        }

    @Suppress("USELESS_ELVIS")
    override suspend fun getSingleTaskGroupById(taskGroupId: Long): LiveData<SingleTaskGroup> {
        singleTaskGroupsDao.getSingleTaskGroupById(taskGroupId) ?: throw NullPointerException()
        return Transformations.map(singleTaskGroupsDao.getSingleTaskGroupByIdObservable(taskGroupId)) {
            it.toSingleTaskGroup()
        }
    }

    override suspend fun getAllSingleTaskGroupEntries(): LiveData<List<SingleTaskGroupEntry>> =
        Transformations.map(singleTaskGroupsDao.getAllGroupsWithSingleTasks()) { taskGroups ->
            taskGroups.map {
                it.toSingleTaskGroupEntry()
            }
        }
}