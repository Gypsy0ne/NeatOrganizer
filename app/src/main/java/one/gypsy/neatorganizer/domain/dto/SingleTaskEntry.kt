package one.gypsy.neatorganizer.domain.dto

data class SingleTaskEntry(
    override val id: Long,
    override val description: String,
    override var done: Boolean
): Task