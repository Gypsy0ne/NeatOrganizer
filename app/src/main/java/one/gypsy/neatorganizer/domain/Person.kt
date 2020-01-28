package one.gypsy.neatorganizer.domain

import android.graphics.Bitmap
import java.util.*


data class Person(val id: Long = 0, val name: String, val photoThumbnail: Bitmap?, val lastInteraction: Int, val dateOfBirth: Date)