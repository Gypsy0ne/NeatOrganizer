package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTaskGroupsDao
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import javax.inject.Inject

class UserSingleTaskGroupsDataSource @Inject constructor(val singleTaskGroupsDao: SingleTaskGroupsDao): SingleTaskGroupsDataSource {
    override suspend fun add(singleTaskGroup: SingleTaskGroup) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}