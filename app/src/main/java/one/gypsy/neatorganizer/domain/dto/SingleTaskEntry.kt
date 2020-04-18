package one.gypsy.neatorganizer.domain.dto

data class SingleTaskEntry(
    override val id: Long = 0,
    override val name: String,
    override var done: Boolean,
    override val groupId: Long
): Task()