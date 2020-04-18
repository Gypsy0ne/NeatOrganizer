package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import javax.inject.Inject

class UserSingleTasksDataSource @Inject constructor(var singleTasksDao: SingleTasksDao): SingleTasksDataSource {
    override suspend fun add(singleTaskEntry: SingleTaskEntry): Long = singleTasksDao.insert(
        SingleTaskEntity(singleTaskEntry.groupId, singleTaskEntry.name, singleTaskEntry.done))
}