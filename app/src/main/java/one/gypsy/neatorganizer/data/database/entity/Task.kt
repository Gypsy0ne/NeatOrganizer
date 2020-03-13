package one.gypsy.neatorganizer.data.database.entity

import androidx.room.PrimaryKey

abstract class Task {
    abstract val description: String
    abstract val done: Boolean
    abstract var id: Long
}