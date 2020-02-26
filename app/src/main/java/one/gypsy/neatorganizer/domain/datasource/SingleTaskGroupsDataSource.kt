package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup

interface SingleTaskGroupsDataSource {
    suspend fun add(singleTaskGroup: SingleTaskGroup): Long
}