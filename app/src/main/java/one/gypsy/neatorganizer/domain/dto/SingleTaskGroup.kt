package one.gypsy.neatorganizer.domain.dto

import androidx.room.PrimaryKey

class SingleTaskGroup(val name: String, var id: Long, val tasks: List<SingleTaskEntry>)