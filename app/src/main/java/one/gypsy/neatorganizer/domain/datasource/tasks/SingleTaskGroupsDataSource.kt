package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup

interface SingleTaskGroupsDataSource {
    suspend fun add(singleTaskGroup: SingleTaskGroup): Long
    suspend fun remove(singleTaskGroup: SingleTaskGroup)
    suspend fun removeById(taskGroupId: Long)
    suspend fun update(singleTaskGroup: SingleTaskGroup)
    suspend fun getAllSingleTaskGroups(): LiveData<List<SingleTaskGroup>>
}