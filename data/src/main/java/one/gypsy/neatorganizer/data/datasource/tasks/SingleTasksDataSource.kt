package one.gypsy.neatorganizer.data.datasource.tasks

import androidx.lifecycle.LiveData
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskEntry

interface SingleTasksDataSource {
    suspend fun add(singleTaskEntry: SingleTaskEntry)
    suspend fun update(singleTaskEntry: SingleTaskEntry)
    suspend fun remove(singleTaskEntry: SingleTaskEntry)
    suspend fun getAllSingleTasksByGroupId(groupId: Long): List<SingleTaskEntry>
    suspend fun getAllSingleTasksByGroupIdObservable(groupId: Long): LiveData<List<SingleTaskEntry>>
}
