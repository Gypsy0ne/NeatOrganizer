package one.gypsy.neatorganizer.presentation.tasks.model

sealed class TaskListItem(
    open val id: Long,
    open val name: String,
    open var visible: Boolean,
    open val groupId: Long

) {
    data class TaskListHeader(
        override val id: Long,
        override val name: String,
        override var visible: Boolean,
        override val groupId: Long = id,
        val subItemsCount: Int = 0,
        var expanded: Boolean = false
    ) : TaskListItem(id, name, visible, id) {
    }

    data class TaskListSubItem(
        override val id: Long,
        override val name: String,
        override var visible: Boolean,
        override val groupId: Long = 0,
        var done: Boolean = false
    ) : TaskListItem(id, name, visible, groupId) {
    }
}

fun TaskListItem.toHeader() =
    TaskListItem.TaskListHeader(
        id,
        name,
        visible
    )

fun TaskListItem.toSubItem() =
    TaskListItem.TaskListSubItem(
        id,
        name,
        visible
    )