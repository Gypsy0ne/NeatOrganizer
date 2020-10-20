package one.gypsy.neatorganizer.data.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks

class SingleTaskGroupsRepository(private val dataSource: SingleTaskGroupsDataSource) {

    suspend fun addSingleTaskGroup(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.add(singleTaskGroupWithTasks)

    suspend fun removeSingleTaskGroup(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.remove(singleTaskGroupWithTasks)

    suspend fun updateSingleTaskGroup(singleTaskGroupWithTasks: SingleTaskGroupWithTasks) =
        dataSource.update(singleTaskGroupWithTasks)

    suspend fun getAllSingleTaskGroups() = dataSource.getAllSingleTaskGroupsWithTasks()

    suspend fun getAllSingleTaskGroupEntries() = dataSource.getAllSingleTaskGroupEntries()

    suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupWithTasksById(taskGroupId)

    suspend fun getSingleTaskGroupById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupById(taskGroupId)

    suspend fun removeSingleTaskGroupById(taskGroupId: Long) = dataSource.removeById(taskGroupId)

}