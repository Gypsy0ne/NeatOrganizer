package one.gypsy.neatorganizer.data.repositories

import one.gypsy.neatorganizer.domain.datasource.SingleTaskGroupsDataSource
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import javax.inject.Inject

class SingleTaskGroupsRepository @Inject constructor(var dataSource: SingleTaskGroupsDataSource) {

    suspend fun addSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.add(singleTaskGroup)

    suspend fun removeSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.remove(singleTaskGroup)

    suspend fun updateSingleTaskGroup(singleTaskGroup: SingleTaskGroup) =
        dataSource.update(singleTaskGroup)

    suspend fun getAllSingleTaskGroups() = dataSource.getAllSingleTaskGroups()

}