package one.gypsy.neatorganizer.presentation.tasks.model

sealed class TaskListItem(
    open val id: Long,
    open val name: String,
    open val visible: Boolean,
    open val groupId: Long,
    open val edited: Boolean

) {
    data class TaskListHeader(
        override val id: Long,
        override val name: String,
        override val visible: Boolean,
        override val groupId: Long = id,
        override val edited: Boolean = false,
        val subItemsCount: Int = 0,
        val expanded: Boolean = false
    ) : TaskListItem(id, name, visible, id, edited) {
    }

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override val visible: Boolean,
        override val groupId: Long = 0,
        override val edited: Boolean = false,
        val done: Boolean = false
    ) : TaskListItem(id, name, visible, groupId, edited) {
    }
}

fun TaskListItem.toHeader(subItemsCount: Int, expanded: Boolean) =
    TaskListItem.TaskListHeader(
        id,
        name,
        visible,
        subItemsCount = subItemsCount,
        expanded = expanded
    )

fun TaskListItem.toSubItem(done: Boolean) =
    TaskListItem.TaskListSubItem(
        id,
        name,
        visible,
        done = done
    )