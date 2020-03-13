package one.gypsy.neatorganizer.domain.dto

interface Task {
    val id: Long
    val description: String
    var done: Boolean
}