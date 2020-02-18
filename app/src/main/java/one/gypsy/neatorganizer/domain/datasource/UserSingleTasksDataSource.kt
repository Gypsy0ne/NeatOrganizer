package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.SingleTaskDao
import javax.inject.Inject

class UserSingleTasksDataSource @Inject constructor(var singleTaskDao: SingleTaskDao): SingleTasksDataSource {
}