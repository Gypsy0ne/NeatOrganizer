package one.gypsy.neatorganizer.domain.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupDto
import one.gypsy.neatorganizer.domain.model.tasks.SingleTaskGroupWithTasks

class SingleTaskGroupsRepository(private val dataSource: SingleTaskGroupsDataSource) {

    suspend fun addSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.addSingleTaskGroupWithTasks(singleTaskGroupWithTasks)

    suspend fun addSingleTaskGroup(singleTaskGroup: SingleTaskGroupDto) =
        dataSource.addSingleTaskGroup(singleTaskGroup)

    suspend fun removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks)

    suspend fun updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks)

    suspend fun updateSingleTaskGroup(singleTaskGroup: SingleTaskGroupDto) =
        dataSource.updateSingleTaskGroup(singleTaskGroup)

    suspend fun getAllSingleTaskGroups() = dataSource.getAllSingleTaskGroupsWithTasks()

    suspend fun getAllSingleTaskGroupEntries() = dataSource.getAllSingleTaskGroupEntries()

    suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupWithTasksById(taskGroupId)

    suspend fun getSingleTaskGroupById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupById(taskGroupId)

    suspend fun removeSingleTaskGroupById(taskGroupId: Long) = dataSource.removeById(taskGroupId)
}
