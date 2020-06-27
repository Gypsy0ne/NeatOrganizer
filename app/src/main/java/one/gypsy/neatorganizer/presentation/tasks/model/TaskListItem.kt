package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.presentation.listing.HeaderItem
import one.gypsy.neatorganizer.presentation.listing.SubItem

sealed class TaskListItem(
    open val id: Long,
    open val name: String,
    open val edited: Boolean
) {
    data class TaskListHeader(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false
    ) : TaskListItem(id = id, name = name, edited = edited), HeaderItem

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false
    ) : TaskListItem(id = id, name = name, edited = edited), SubItem
}

fun TaskListItem.TaskListHeader.toSingleTaskGroup(
    taskEntries: List<SingleTaskEntry> = emptyList()
) = SingleTaskGroup(name = this.name, id = this.id, tasks = taskEntries)

fun TaskListItem.TaskListSubItem.toSingleTask() =
    SingleTaskEntry(id = this.id, name = this.name, done = this.done, groupId = this.groupId)