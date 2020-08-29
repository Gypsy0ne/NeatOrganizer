package one.gypsy.neatorganizer.data.database.entity.people

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithInteractionHistory(@Embedded val person: PersonEntity, @Relation(parentColumn = "id", entityColumn = "personProfileId") val interactionHistory: List<InteractionEntryEntity>)