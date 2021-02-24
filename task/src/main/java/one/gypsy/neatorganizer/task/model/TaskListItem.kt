package one.gypsy.neatorganizer.task.model

import one.gypsy.neatorganizer.core.listing.Editable
import one.gypsy.neatorganizer.core.listing.HeaderItem
import one.gypsy.neatorganizer.core.listing.Listed
import one.gypsy.neatorganizer.core.listing.SubItem
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntryDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupDto
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasksDto

internal sealed class TaskListItem(
    override val id: Long,
    override val title: String,
    override val edited: Boolean
) : Listed, Editable {
    data class TaskListHeader(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false,
        val createdAt: Long
    ) : TaskListItem(id = id, title = title, edited = edited), HeaderItem

    data class TaskListSubItem(
        override val id: Long,
        override val title: String,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false,
        val createdAt: Long
    ) : TaskListItem(id = id, title = title, edited = edited), SubItem
}

internal fun TaskListItem.TaskListHeader.toSingleTaskGroup(
    taskEntries: List<SingleTaskEntryDto> = emptyList()
) = SingleTaskGroupWithTasksDto(
    taskGroup = SingleTaskGroupDto(id = this.id, name = this.title, createdAt = this.createdAt),
    tasks = taskEntries
)

internal fun TaskListItem.TaskListSubItem.toSingleTask() =
    SingleTaskEntryDto(
        id = this.id,
        name = this.title,
        done = this.done,
        groupId = this.groupId,
        createdAt = this.createdAt
    )
