package one.gypsy.neatorganizer.data.repositories

import one.gypsy.neatorganizer.domain.datasource.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import javax.inject.Inject

class SingleTasksRepository @Inject constructor(var dataSource: SingleTasksDataSource) {
    suspend fun addSingleTask(singleTask: SingleTaskEntry) = dataSource.add(singleTask)
    suspend fun updateSingleTask(singleTask: SingleTaskEntry) = dataSource.update(singleTask)

}