package one.gypsy.neatorganizer.domain.dto.people

import android.graphics.Bitmap
import java.util.*

data class PersonProfile(
    override val name: String,
    override val sex: Person.Sex,
    override val photoThumbnail: Bitmap?,
    override val lastInteraction: Int,
    override val dateOfBirth: Date,
    val interactionHistory: List<InteractionEntry>
) : Person
