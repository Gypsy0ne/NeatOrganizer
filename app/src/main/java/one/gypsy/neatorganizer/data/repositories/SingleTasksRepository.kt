package one.gypsy.neatorganizer.data.repositories

import one.gypsy.neatorganizer.domain.datasource.SingleTasksDataSource
import javax.inject.Inject

class SingleTasksRepository @Inject constructor(var dataSource: SingleTasksDataSource) {

}