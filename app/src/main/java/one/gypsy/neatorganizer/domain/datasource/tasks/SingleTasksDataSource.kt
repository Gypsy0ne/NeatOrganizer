package one.gypsy.neatorganizer.domain.datasource.tasks

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry

interface SingleTasksDataSource {
    suspend fun add(singleTaskEntry: SingleTaskEntry): Long
    suspend fun update(singleTaskEntry: SingleTaskEntry)
    suspend fun remove(singleTaskEntry: SingleTaskEntry)
    suspend fun getAllSingleTasksByGroupId(groupId: Long): List<SingleTaskEntry>
}