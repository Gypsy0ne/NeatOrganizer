package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.people.InteractionsDao
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import javax.inject.Inject

class UserInteractionDataSource @Inject constructor(val interactionsDao: InteractionsDao): InteractionDataSource {
    override suspend fun add(interactionEntry: InteractionEntry) = interactionsDao.insert(
        InteractionEntryEntity(
            interactionEntry.profileId,
            interactionEntry.interactionDate
        )
    )
}