package one.gypsy.neatorganizer.domain.dto.routines

import one.gypsy.neatorganizer.domain.dto.Task

data class RoutineTaskEntry(
    override val id: Long,
    override val groupId: Long,
    override val name: String,
    override var done: Boolean
): Task()