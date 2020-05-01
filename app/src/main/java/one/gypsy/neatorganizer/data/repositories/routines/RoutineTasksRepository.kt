package one.gypsy.neatorganizer.data.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutineTasksDataSource
import javax.inject.Inject

class RoutineTasksRepository @Inject constructor(var dataSource: RoutineTasksDataSource)