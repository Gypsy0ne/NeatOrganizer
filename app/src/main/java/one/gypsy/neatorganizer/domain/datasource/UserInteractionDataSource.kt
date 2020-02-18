package one.gypsy.neatorganizer.domain.datasource

import one.gypsy.neatorganizer.data.database.dao.InteractionDao
import one.gypsy.neatorganizer.data.database.entity.InteractionEntryEntity
import one.gypsy.neatorganizer.domain.dto.InteractionEntry
import javax.inject.Inject

class UserInteractionDataSource @Inject constructor(val interactionDao: InteractionDao): InteractionDataSource {
    override suspend fun add(interactionEntry: InteractionEntry) = interactionDao.insert(
        InteractionEntryEntity(interactionEntry.profileId, interactionEntry.interactionDate)
    )
}