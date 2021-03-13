package one.gypsy.neatorganizer.domain.repositories.tasks

import androidx.lifecycle.map
import one.gypsy.neatorganizer.data.datasource.tasks.SingleTasksDataSource
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntryDto
import one.gypsy.neatorganizer.domain.dto.tasks.toDto
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskEntry

class SingleTasksRepository(private val dataSource: SingleTasksDataSource) {

    suspend fun addSingleTask(singleTask: SingleTaskEntryDto) =
        dataSource.add(singleTask.toSingleTaskEntry())

    suspend fun updateSingleTask(singleTask: SingleTaskEntryDto) =
        dataSource.update(singleTask.toSingleTaskEntry())

    suspend fun removeSingleTask(singleTask: SingleTaskEntryDto) =
        dataSource.remove(singleTask.toSingleTaskEntry())

    suspend fun getAllSingleTasksByGroupId(groupId: Long) =
        dataSource.getAllSingleTasksByGroupId(groupId).map { it.toDto() }

    suspend fun getAllSingleTasksByGroupIdObservable(groupId: Long) =
        dataSource.getAllSingleTasksByGroupIdObservable(groupId).map { tasks ->
            tasks.map { it.toDto() }
        }
}
