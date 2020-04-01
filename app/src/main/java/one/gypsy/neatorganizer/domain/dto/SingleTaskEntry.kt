package one.gypsy.neatorganizer.domain.dto

data class SingleTaskEntry(
    override val id: Long,
    override val name: String,
    override var done: Boolean
): Task()