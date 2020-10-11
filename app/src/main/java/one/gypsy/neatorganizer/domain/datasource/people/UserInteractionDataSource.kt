package one.gypsy.neatorganizer.domain.datasource.people

import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import one.gypsy.neatorganizer.domain.dto.people.InteractionEntry

class UserInteractionDataSource(private val interactionsDao: InteractionsDao) :
    InteractionDataSource {
    override suspend fun add(interactionEntry: InteractionEntry) = interactionsDao.insert(
        InteractionEntryEntity(
            interactionEntry.profileId,
            interactionEntry.interactionDate,
            interactionEntry.rating
        )
    )
}