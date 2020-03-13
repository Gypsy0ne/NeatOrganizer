package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.tasks.SingleTasksDao
import javax.inject.Inject

class UserSingleTasksDataSource @Inject constructor(var singleTasksDao: SingleTasksDao): SingleTasksDataSource {
}