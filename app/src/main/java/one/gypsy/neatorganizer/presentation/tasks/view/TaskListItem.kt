package one.gypsy.neatorganizer.presentation.tasks.view

import one.gypsy.neatorganizer.domain.dto.Task

data class TaskListItem(
    override val id: Long,
    override val name: String,
    override var done: Boolean,
    val groupId: Long,
    val grouping: Boolean
): Task()