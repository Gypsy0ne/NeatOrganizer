package one.gypsy.neatorganizer.domain.dto.people

import android.graphics.Bitmap
import java.util.*

data class PersonEntry(
    val id: Long = 0,
    override val name: String,
    override val sex: Person.Sex,
    override val photoThumbnail: Bitmap?,
    override val lastInteraction: Int,
    override val dateOfBirth: Date
) : Person