package one.gypsy.neatorganizer.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks

class SingleTaskGroupsRepository(private val dataSource: SingleTaskGroupsDataSource) {

    suspend fun addSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.addSingleTaskGroupWithTasks(singleTaskGroupWithTasks)

    suspend fun addSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.addSingleTaskGroup(singleTaskGroup)

    suspend fun removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks)

    suspend fun updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks)

    suspend fun updateSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.updateSingleTaskGroup(singleTaskGroup)

    suspend fun getAllSingleTaskGroups() = dataSource.getAllSingleTaskGroupsWithTasks()

    suspend fun getAllSingleTaskGroupEntries() = dataSource.getAllSingleTaskGroupEntries()

    suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupWithTasksById(taskGroupId)

    suspend fun getSingleTaskGroupById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupById(taskGroupId)

    suspend fun removeSingleTaskGroupById(taskGroupId: Long) = dataSource.removeById(taskGroupId)
}
