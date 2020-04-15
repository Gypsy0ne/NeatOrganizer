package one.gypsy.neatorganizer.domain.dto

data class SingleTaskEntry(
    override val groupId: Long,
    override val name: String,
    override var done: Boolean,
    override val id: Long = 0
): Task()