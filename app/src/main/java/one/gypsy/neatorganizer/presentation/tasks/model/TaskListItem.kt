package one.gypsy.neatorganizer.presentation.tasks.model

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
        val subItemsCount: Int = 0,
        val expanded: Boolean = false
    ) : TaskListItem(id = id, name = name, visible = true, edited = edited)

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override val visible: Boolean,
        override val edited: Boolean = false,
        val groupId: Long,
        val done: Boolean = false
    ) : TaskListItem(id = id, name = name, visible = visible, edited = edited)
}

fun TaskListItem.changeVisibility(visible: Boolean) = when (this) {
    is TaskListItem.TaskListHeader -> this
    is TaskListItem.TaskListSubItem -> this.copy(visible = visible)
}
//
//fun TaskListItem.toHeader(subItemsCount: Int, expanded: Boolean) =
//    TaskListItem.TaskListHeader(
//        id,
//        name,
//        visible,
//        subItemsCount = subItemsCount,
//        expanded = expanded
//    )
//
//fun TaskListItem.toSubItem(done: Boolean) =
//    TaskListItem.TaskListSubItem(
//        id,
//        name,
//        visible,
//        done = done
//    )