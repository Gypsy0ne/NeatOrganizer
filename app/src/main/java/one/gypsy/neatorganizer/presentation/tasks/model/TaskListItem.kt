package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.presentation.listing.HeaderItem
import one.gypsy.neatorganizer.presentation.listing.SubItem

sealed class TaskListItem(
    open val id: Long,
    open val name: String,
    open val visible: Boolean,
    open val edited: Boolean
) {
    //header is always visible so visible is necessary
    data class TaskListHeader(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        override val subItemsCount: Int = 0,
        override val expanded: Boolean = false
    ) : TaskListItem(id = id, name = name, visible = true, edited = edited), HeaderItem

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override val visible: Boolean,
        override val edited: Boolean = false,
        override val groupId: Long,
        override val done: Boolean = false
    ) : TaskListItem(id = id, name = name, visible = visible, edited = edited), SubItem
}

fun TaskListItem.changeVisibility(visible: Boolean) = when (this) {
    is TaskListItem.TaskListHeader -> this
    is TaskListItem.TaskListSubItem -> this.copy(visible = visible)
}
