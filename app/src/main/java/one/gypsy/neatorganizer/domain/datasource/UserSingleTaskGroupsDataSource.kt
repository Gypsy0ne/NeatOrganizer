package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import javax.inject.Inject

class UserSingleTaskGroupsDataSource @Inject constructor(val singleTaskGroupsDao: SingleTaskGroupsDao): SingleTaskGroupsDataSource {
    override suspend fun add(singleTaskGroup: SingleTaskGroup) = singleTaskGroupsDao.insert(SingleTaskGroupEntity(singleTaskGroup.name))

}