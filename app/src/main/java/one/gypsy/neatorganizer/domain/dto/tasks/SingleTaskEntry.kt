package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.domain.dto.Task

data class SingleTaskEntry(
    override val id: Long = 0,
    override val name: String,
    override var done: Boolean,
    override val groupId: Long
): Task()