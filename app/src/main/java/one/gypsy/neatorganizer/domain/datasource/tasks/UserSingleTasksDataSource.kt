package one.gypsy.neatorganizer.domain.datasource.tasks

import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.toSingleTaskEntity

class UserSingleTasksDataSource(var singleTasksDao: SingleTasksDao) :
    SingleTasksDataSource {
    override suspend fun add(singleTaskEntry: SingleTaskEntry): Long =
        singleTasksDao.insert(singleTaskEntry.toSingleTaskEntity())

    override suspend fun update(singleTaskEntry: SingleTaskEntry) =
        singleTasksDao.update(singleTaskEntry.toSingleTaskEntity())

    override suspend fun remove(singleTaskEntry: SingleTaskEntry) =
        singleTasksDao.delete(singleTaskEntry.toSingleTaskEntity())
}