package one.gypsy.neatorganizer.domain.repositories.tasks

import androidx.lifecycle.map
import one.gypsy.neatorganizer.data.datasource.tasks.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto
import one.gypsy.neatorganizer.domain.dto.tasks.toDto
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskGroupWithTasks

class SingleTaskGroupsRepository(private val dataSource: SingleTaskGroupsDataSource) {

    suspend fun addSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasksDto) =
        dataSource.addSingleTaskGroupWithTasks(singleTaskGroupWithTasks.toSingleTaskGroupWithTasks())

    suspend fun addSingleTaskGroup(singleTaskGroup: SingleTaskGroupDto) =
        dataSource.addSingleTaskGroup(singleTaskGroup.toSingleTaskGroup())

    suspend fun removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasksDto) =
        dataSource.removeSingleTaskGroupWithTasks(singleTaskGroupWithTasks.toSingleTaskGroupWithTasks())

    suspend fun updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks: SingleTaskGroupWithTasksDto) =
        dataSource.updateSingleTaskGroupWithTasks(singleTaskGroupWithTasks.toSingleTaskGroupWithTasks())

    suspend fun updateSingleTaskGroup(singleTaskGroup: SingleTaskGroupDto) =
        dataSource.updateSingleTaskGroup(singleTaskGroup.toSingleTaskGroup())

    suspend fun getAllSingleTaskGroups() =
        dataSource.getAllSingleTaskGroupsWithTasks().map { taskGroups ->
            taskGroups.map { it.toDto() }
        }

    suspend fun getAllSingleTaskGroupEntries() =
        dataSource.getAllSingleTaskGroupEntries().map { entries ->
            entries.map { it.toDto() }
        }

    suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupWithTasksById(taskGroupId).map { it.toDto() }

    suspend fun getSingleTaskGroupById(taskGroupId: Long) =
        dataSource.getSingleTaskGroupById(taskGroupId).map { it.toDto() }

    suspend fun removeSingleTaskGroupById(taskGroupId: Long) = dataSource.removeById(taskGroupId)
}
