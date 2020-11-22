package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks

class TaskListMapper {

    fun mapTasksToListItems(
        tasksGroupWithTasks: List<SingleTaskGroupWithTasks>,
        oldList: List<TaskListItem>
    ) = mutableListOf<TaskListItem>().apply {
        val oldHeaders = oldList.filterIsInstance<TaskListItem.TaskListHeader>()
        tasksGroupWithTasks.forEach { taskGroup ->
            this.addAll(
                mapTaskGroupToTaskListItems(
                    taskGroup,
                    wasHeaderExpanded(oldHeaders, taskGroup)
                )
            )
        }
    }

    private fun wasHeaderExpanded(
        oldHeaders: List<TaskListItem.TaskListHeader>,
        taskGroupWithTasks: SingleTaskGroupWithTasks
    ) = oldHeaders.firstOrNull { it.id == taskGroupWithTasks.id }?.expanded ?: false

    private fun mapTaskGroupToTaskListItems(
        taskGroupWithTasks: SingleTaskGroupWithTasks,
        expandedHeader: Boolean = false
    ): List<TaskListItem> = mutableListOf<TaskListItem>().apply {
        with(taskGroupWithTasks.toTaskListHeader(expandedHeader)) {
            add(this)
            addAll(mapTasksToListSubItems(taskGroupWithTasks.tasks))
        }
    }

    private fun mapTasksToListSubItems(
        tasks: List<SingleTaskEntry>
    ) = List(tasks.size) {
        tasks[it].toTaskListSubItem()
    }

    fun getVisibleItems(items: List<TaskListItem>): List<TaskListItem> =
        items.partition { it is TaskListItem.TaskListHeader }
            .let { partedLists ->
                mutableListOf<TaskListItem>().apply {
                    partedLists.first.filterIsInstance<TaskListItem.TaskListHeader>()
                        .forEach { header ->
                            this.addAll(
                                getHeaderWithItemsIfExpanded(
                                    header, partedLists.second
                                )
                            )
                        }
                }
            }

    private fun getHeaderWithItemsIfExpanded(
        header: TaskListItem.TaskListHeader,
        subItems: List<TaskListItem>
    ) = mutableListOf<TaskListItem>().apply {
        add(header)
        if (header.expanded) {
            addAll(
                subItems.filter { shouldAddToGroup(it, header.id) }
            )
        }
    }

    private fun shouldAddToGroup(
        listItem: TaskListItem,
        headerId: Long
    ) = listItem is TaskListItem.TaskListSubItem && headerId == listItem.groupId


    fun updateExpansion(headerItemId: Long, oldList: List<TaskListItem>?) =
        oldList?.map { negateExpandedIfHeader(it, headerItemId) }

    private fun negateExpandedIfHeader(
        listedItem: TaskListItem,
        headerItemId: Long
    ) = if (listedItem is TaskListItem.TaskListHeader && listedItem.id == headerItemId) {
        listedItem.copy(expanded = !listedItem.expanded)
    } else {
        listedItem
    }

}