package one.gypsy.neatorganizer.data.repositories.people

import one.gypsy.neatorganizer.domain.datasource.people.InteractionDataSource
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry

class InteractionRepository(private val dataSource: InteractionDataSource) {
    suspend fun addInteractionEntry(interactionEntry: InteractionEntry) =
        dataSource.add(interactionEntry)
}