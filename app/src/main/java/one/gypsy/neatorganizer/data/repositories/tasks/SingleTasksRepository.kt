package one.gypsy.neatorganizer.data.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry

class SingleTasksRepository(private val dataSource: SingleTasksDataSource) {
    suspend fun addSingleTask(singleTask: SingleTaskEntry) = dataSource.add(singleTask)
    suspend fun updateSingleTask(singleTask: SingleTaskEntry) = dataSource.update(singleTask)
    suspend fun removeSingleTask(singleTask: SingleTaskEntry) = dataSource.remove(singleTask)
}