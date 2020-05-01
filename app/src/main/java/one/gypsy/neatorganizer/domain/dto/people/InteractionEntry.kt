package one.gypsy.neatorganizer.domain.dto.people

import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import java.util.*

data class InteractionEntry(
    val id: Long = 0,
    val profileId: Long,
    val interactionDate: Date,
    val rating: Int
)

fun InteractionEntry.toInteractionEntryEntity() = InteractionEntryEntity(
    personProfileId = this.profileId,
    creationDate = this.interactionDate,
    rating = this.rating,
    id = this.id
)
