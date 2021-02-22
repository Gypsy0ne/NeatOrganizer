package one.gypsy.neatorganizer.domain.repositories.tasks

import one.gypsy.neatorganizer.domain.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.model.tasks.SingleTaskEntry

class SingleTasksRepository(private val dataSource: SingleTasksDataSource) {

    suspend fun addSingleTask(singleTask: SingleTaskEntry) = dataSource.add(singleTask)

    suspend fun updateSingleTask(singleTask: SingleTaskEntry) = dataSource.update(singleTask)

    suspend fun removeSingleTask(singleTask: SingleTaskEntry) = dataSource.remove(singleTask)

    suspend fun getAllSingleTasksByGroupId(groupId: Long) =
        dataSource.getAllSingleTasksByGroupId(groupId)

    suspend fun getAllSingleTasksByGroupIdObservable(groupId: Long) =
        dataSource.getAllSingleTasksByGroupIdObservable(groupId)
}
