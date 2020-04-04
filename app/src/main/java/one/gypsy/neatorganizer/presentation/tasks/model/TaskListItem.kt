package one.gypsy.neatorganizer.presentation.tasks.model

sealed class TaskListItem(
    open val id: Long,
    open val name: String,
    open val groupId: Long
) {
    data class TaskListHeader(
        override val id: Long,
        override val name: String,
        override val groupId: Long
    ) : TaskListItem(id, name, groupId) {
    }

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override val groupId: Long,
        var done: Boolean = false
    ) : TaskListItem(id, name, groupId) {
    }
}

fun TaskListItem.toHeader() =
    TaskListItem.TaskListHeader(
        id,
        name,
        groupId
    )
fun TaskListItem.toSubItem() =
    TaskListItem.TaskListSubItem(
        id,
        name,
        groupId
    )