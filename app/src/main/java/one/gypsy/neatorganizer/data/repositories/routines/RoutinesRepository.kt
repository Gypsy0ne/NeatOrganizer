package one.gypsy.neatorganizer.data.repositories.routines

import one.gypsy.neatorganizer.domain.datasource.routines.RoutinesDataSource
import javax.inject.Inject

class RoutinesRepository @Inject constructor(var dataSource: RoutinesDataSource)