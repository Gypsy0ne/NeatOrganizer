package one.gypsy.neatorganizer.data.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup

class SingleTaskGroupsRepository(private val dataSource: SingleTaskGroupsDataSource) {

    suspend fun addSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.add(singleTaskGroup)

    suspend fun removeSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.remove(singleTaskGroup)

    suspend fun updateSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.update(singleTaskGroup)

    suspend fun getAllSingleTaskGroups() = dataSource.getAllSingleTaskGroups()

    suspend fun getAllSingleTaskGroupEntries() = dataSource.getAllSingleTaskGroupEntries()

    suspend fun getAllSingleTasksByGroupId(groupId: Long) =
        dataSource.getAllSingleTaskGroupEntries()

    suspend fun getSingleTaskGroupById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupById(taskGroupId)

    suspend fun removeSingleTaskGroupById(taskGroupId: Long) = dataSource.removeById(taskGroupId)

}