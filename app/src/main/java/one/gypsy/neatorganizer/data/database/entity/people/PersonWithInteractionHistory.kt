package one.gypsy.neatorganizer.data.database.entity.people

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.data.database.entity.people.InteractionEntryEntity
import one.gypsy.neatorganizer.data.database.entity.people.PersonEntity

data class PersonWithInteractionHistory(@Embedded val person: PersonEntity, @Relation(parentColumn = "id", entityColumn = "personProfileId") val interactionHistory: List<InteractionEntryEntity>)