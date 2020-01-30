package one.gypsy.neatorganizer.domain.dto

import android.graphics.Bitmap
import java.util.*


data class Person(val id: Long = 0, val name: String, val sex: Sex, val photoThumbnail: Bitmap?, val lastInteraction: Int, val dateOfBirth: Date) {
    enum class Sex(val sex: String) {
        MALE("M"),
        FEMALE("F")
    }
}