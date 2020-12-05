package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.presentation.common.listing.HeaderItem
import one.gypsy.neatorganizer.presentation.common.listing.ListedItem
import one.gypsy.neatorganizer.presentation.common.listing.SubItem

sealed class TaskListItem(
    override val id: Long,
    override val name: String,
    override val edited: Boolean
) : ListedItem, Timestamped {
    data class TaskListHeader(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false,
        override val createdAt: Long
    ) : TaskListItem(id = id, name = name, edited = edited), HeaderItem

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false,
        override val createdAt: Long
    ) : TaskListItem(id = id, name = name, edited = edited), SubItem
}

fun TaskListItem.TaskListHeader.toSingleTaskGroup(
    taskEntries: List<SingleTaskEntry> = emptyList()
) = SingleTaskGroupWithTasks(
    name = this.name,
    id = this.id,
    tasks = taskEntries,
    createdAt = this.createdAt
)

fun TaskListItem.TaskListSubItem.toSingleTask() =
    SingleTaskEntry(
        id = this.id,
        name = this.name,
        done = this.done,
        groupId = this.groupId,
        createdAt = this.createdAt
    )
