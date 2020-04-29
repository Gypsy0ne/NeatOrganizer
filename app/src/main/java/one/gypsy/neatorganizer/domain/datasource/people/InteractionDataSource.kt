package one.gypsy.neatorganizer.domain.datasource.people

import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry

interface InteractionDataSource {
    suspend fun add(interactionEntry: InteractionEntry): Long
}