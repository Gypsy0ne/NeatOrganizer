package one.gypsy.neatorganizer.domain.dto

import androidx.room.PrimaryKey

data class SingleTaskGroup(val name: String, var id: Long = 0, val tasks: List<SingleTaskEntry>? = null)