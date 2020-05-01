package one.gypsy.neatorganizer.presentation.tasks.model

import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup

class TaskListMapper {

    fun flattenTaskGroupsToList(
        taskGroups: List<SingleTaskGroup>,
        expandedItemsIds: List<Long>
    ): List<TaskListItem> {
        val taskListItems = mutableListOf<TaskListItem>()

        taskGroups.sortedByDescending { it.id }.forEach { taskGroup ->
            val expanded = expandedItemsIds.contains(taskGroup.id)
            taskListItems.add(
                taskGroup.toTaskListHeader(expanded)
            )
            taskListItems.addAll(mapTaskEntriesOntoSubItems(taskGroup, expanded))
        }
        return taskListItems
    }

    private fun mapTaskEntriesOntoSubItems(
        taskGroup: SingleTaskGroup,
        visible: Boolean
    ): List<TaskListItem> {
        return taskGroup.tasks?.sortedByDescending { it.id }?.map { taskEntry ->
            taskEntry.toTaskListSubItem(visible)
        } ?: emptyList()
    }
}