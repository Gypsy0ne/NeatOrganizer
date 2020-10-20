package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks

interface SingleTaskGroupsDataSource {
    suspend fun add(singleTaskGroupWithTasks: SingleTaskGroupWithTasks): Long
    suspend fun remove(singleTaskGroupWithTasks: SingleTaskGroupWithTasks)
    suspend fun removeById(taskGroupId: Long)
    suspend fun update(singleTaskGroupWithTasks: SingleTaskGroupWithTasks)
    suspend fun getAllSingleTaskGroupsWithTasks(): LiveData<List<SingleTaskGroupWithTasks>>
    suspend fun getSingleTaskGroupWithTasksById(taskGroupId: Long): LiveData<SingleTaskGroupWithTasks>
    suspend fun getSingleTaskGroupById(taskGroupId: Long): LiveData<SingleTaskGroup>
    suspend fun getAllSingleTaskGroupEntries(): LiveData<List<SingleTaskGroupEntry>>
}