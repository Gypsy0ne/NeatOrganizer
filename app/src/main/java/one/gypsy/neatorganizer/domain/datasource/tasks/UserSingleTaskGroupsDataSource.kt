package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.entity.tasks.GroupWithSingleTasks
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import javax.inject.Inject

class UserSingleTaskGroupsDataSource @Inject constructor(val singleTaskGroupsDao: SingleTaskGroupsDao) :
    SingleTaskGroupsDataSource {
    override suspend fun add(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.insert(SingleTaskGroupEntity(name = singleTaskGroup.name))

    override suspend fun remove(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.delete(
            SingleTaskGroupEntity(
                id = singleTaskGroup.id,
                name = singleTaskGroup.name
            )
        )

    override suspend fun getAllSingleTaskGroups(): LiveData<List<SingleTaskGroup>> =
        Transformations.map(singleTaskGroupsDao.getAllGroupsWithSingleTasks()) { taskGroups ->
            mapTaskGroupsEntitiesToEntries(taskGroups)
        }

    override suspend fun update(singleTaskGroup: SingleTaskGroup) =
        singleTaskGroupsDao.update(SingleTaskGroupEntity(singleTaskGroup.name, singleTaskGroup.id))

    private fun mapTaskGroupsEntitiesToEntries(taskGroupEntities: List<GroupWithSingleTasks>) =
        taskGroupEntities.map {
            SingleTaskGroup(
                it.group.name,
                it.group.id
            ).apply {
                this.tasks = mapTaskEntityToEntries(it.tasks)
            }
        }

    private fun mapTaskEntityToEntries(taskEntities: List<SingleTaskEntity>) =
        taskEntities.map {
            SingleTaskEntry(
                id = it.id,
                name = it.name,
                done = it.done,
                groupId = it.groupId
            )
        }
}