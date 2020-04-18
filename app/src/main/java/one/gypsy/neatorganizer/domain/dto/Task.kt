package one.gypsy.neatorganizer.domain.dto

abstract class Task {
    abstract val id: Long
    abstract val name: String
    abstract var done: Boolean
    abstract val groupId: Long
}