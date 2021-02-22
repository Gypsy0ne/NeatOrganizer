package one.gypsy.neatorganizer.data.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.data.model.tasks.toSingleTaskGroup
import one.gypsy.neatorganizer.data.model.tasks.toSingleTaskGroupEntity
import one.gypsy.neatorganizer.data.model.tasks.toSingleTaskGroupEntry
import one.gypsy.neatorganizer.data.model.tasks.toSingleTaskGroupWithTasks
import one.gypsy.neatorganizer.database.dao.tasks.SingleTaskGroupsDao

class UserSingleTaskGroupsDataSource(private val singleTaskGroupsDao: SingleTaskGroupsDao) :
    SingleTaskGroupsDataSource {

    override suspend fun addSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        singleTaskGroupsDao.insert(singleTaskGroupWithTasks.toSingleTaskGroupEntity())

    override suspend fun addSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.insert(singleTaskGroup.toSingleTaskGroupEntity())

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

    @Suppress("USELESS_ELVIS")
    override suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long): LiveData<SingleTaskGroupWithTasks> {
        singleTaskGroupsDao.getSingleTaskGroupById(taskGroupId) ?: throw NullPointerException()
        return Transformations.map(singleTaskGroupsDao.getGroupWithSingleTasksById(taskGroupId)) {
            it.toSingleTaskGroupWithTasks()
        }
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
