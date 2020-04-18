package one.gypsy.neatorganizer.domain.datasource

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup

interface SingleTaskGroupsDataSource {
    suspend fun add(singleTaskGroup: SingleTaskGroup): Long
    suspend fun update(singleTaskGroup: SingleTaskGroup)
    suspend fun getAllSingleTaskGroups(): LiveData<List<SingleTaskGroup>>
}