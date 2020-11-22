package one.gypsy.neatorganizer.domain.datasource.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.data.database.entity.tasks.toSingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskEntity

class UserSingleTasksDataSource(private val singleTasksDao: SingleTasksDao) :
    SingleTasksDataSource {
    override suspend fun add(singleTaskEntry: SingleTaskEntry): Long =
        singleTasksDao.insert(singleTaskEntry.toSingleTaskEntity())

    override suspend fun update(singleTaskEntry: SingleTaskEntry) =
        singleTasksDao.update(singleTaskEntry.toSingleTaskEntity())

    override suspend fun remove(singleTaskEntry: SingleTaskEntry) =
        singleTasksDao.delete(singleTaskEntry.toSingleTaskEntity())

    override suspend fun getAllSingleTasksByGroupIdObservable(groupId: Long): LiveData<List<SingleTaskEntry>> =
        Transformations.map(singleTasksDao.getAllSingleTasksByGroupIdObservable(groupId)) { tasks ->
            tasks.map { it.toSingleTaskEntry() }
        }

    override suspend fun getAllSingleTasksByGroupId(groupId: Long): List<SingleTaskEntry> =
        singleTasksDao.getAllSingleTasksByGroupId(groupId).map { it.toSingleTaskEntry() }
}