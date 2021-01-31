package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.presentation.common.listing.Editable
import one.gypsy.neatorganizer.presentation.common.listing.HeaderItem
import one.gypsy.neatorganizer.presentation.common.listing.Listed
import one.gypsy.neatorganizer.presentation.common.listing.SubItem

sealed class TaskListItem(
    override val id: Long,
    override val title: String,
    override val edited: Boolean
) : Listed, Timestamped, Editable {
    data class TaskListHeader(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false,
        override val createdAt: Long
    ) : TaskListItem(id = id, title = title, edited = edited), HeaderItem

    data class TaskListSubItem(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false,
        override val createdAt: Long
    ) : TaskListItem(id = id, title = title, edited = edited), SubItem
}

fun TaskListItem.TaskListHeader.toSingleTaskGroup(
    taskEntries: List<SingleTaskEntry> = emptyList()
) = SingleTaskGroupWithTasks(
    taskGroup = SingleTaskGroup(id = this.id, name = this.title, createdAt = this.createdAt),
    tasks = taskEntries
)

fun TaskListItem.TaskListSubItem.toSingleTask() =
    SingleTaskEntry(
        id = this.id,
        name = this.title,
        done = this.done,
        groupId = this.groupId,
        createdAt = this.createdAt
    )
