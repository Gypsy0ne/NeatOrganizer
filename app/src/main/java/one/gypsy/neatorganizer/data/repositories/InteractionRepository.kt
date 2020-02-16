package one.gypsy.neatorganizer.data.repositories

import one.gypsy.neatorganizer.domain.datasource.InteractionDataSource
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import javax.inject.Inject

class InteractionRepository @Inject constructor(var dataSource: InteractionDataSource) {
    suspend fun addInteractionEntry(interactionEntry: InteractionEntry) = dataSource.add(interactionEntry)
}