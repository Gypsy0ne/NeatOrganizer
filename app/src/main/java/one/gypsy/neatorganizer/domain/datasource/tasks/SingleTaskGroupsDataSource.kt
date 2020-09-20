package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry

interface SingleTaskGroupsDataSource {
    suspend fun add(singleTaskGroup: SingleTaskGroup): Long
    suspend fun remove(singleTaskGroup: SingleTaskGroup)
    suspend fun removeById(taskGroupId: Long)
    suspend fun update(singleTaskGroup: SingleTaskGroup)
    suspend fun getAllSingleTaskGroups(): LiveData<List<SingleTaskGroup>>
    suspend fun getAllSingleTaskGroupEntries(): LiveData<List<SingleTaskGroupEntry>>
}