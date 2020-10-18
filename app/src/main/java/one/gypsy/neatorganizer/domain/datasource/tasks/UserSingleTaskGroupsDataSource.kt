package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.entity.tasks.toSingleTaskGroup
import one.gypsy.neatorganizer.data.database.entity.tasks.toSingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskGroupEntity

class UserSingleTaskGroupsDataSource(private val singleTaskGroupsDao: SingleTaskGroupsDao) :
    SingleTaskGroupsDataSource {
    override suspend fun add(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.insert(singleTaskGroup.toSingleTaskGroupEntity())

    override suspend fun remove(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.delete(singleTaskGroup.toSingleTaskGroupEntity())

    override suspend fun removeById(taskGroupId: Long) =
        singleTaskGroupsDao.deleteTaskGroupById(taskGroupId)

    override suspend fun update(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.update(singleTaskGroup.toSingleTaskGroupEntity())

    override suspend fun getAllSingleTaskGroups(): LiveData<List<SingleTaskGroup>> =
        Transformations.map(singleTaskGroupsDao.getAllGroupsWithSingleTasks()) { taskGroups ->
            taskGroups.map {
                it.toSingleTaskGroup()
            }
        }

    override suspend fun getSingleTaskGroupById(taskGroupId: Long): LiveData<SingleTaskGroup> =
        Transformations.map(singleTaskGroupsDao.getGroupWithSingleTasksById(taskGroupId)) {
            it.toSingleTaskGroup()
        }

    override suspend fun getAllSingleTaskGroupEntries(): LiveData<List<SingleTaskGroupEntry>> =
        Transformations.map(singleTaskGroupsDao.getAllGroupsWithSingleTasks()) { taskGroups ->
            taskGroups.map {
                it.toSingleTaskGroupEntry()
            }
        }

}