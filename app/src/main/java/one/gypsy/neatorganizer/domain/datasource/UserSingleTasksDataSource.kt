package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import javax.inject.Inject

class UserSingleTasksDataSource @Inject constructor(var singleTasksDao: SingleTasksDao) :
    SingleTasksDataSource {
    override suspend fun add(singleTaskEntry: SingleTaskEntry): Long = singleTasksDao.insert(
        SingleTaskEntity(
            id = singleTaskEntry.id,
            groupId = singleTaskEntry.groupId,
            done = singleTaskEntry.done,
            name = singleTaskEntry.name
        )
    )

    override suspend fun update(singleTaskEntry: SingleTaskEntry) = singleTasksDao.update(
        SingleTaskEntity(
            id = singleTaskEntry.id,
            groupId = singleTaskEntry.groupId,
            name = singleTaskEntry.name,
            done = singleTaskEntry.done
        )
    )
}