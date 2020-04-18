package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.domain.dto.InteractionEntry

interface InteractionDataSource {
    suspend fun add(interactionEntry: InteractionEntry): Long
}