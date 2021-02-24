package one.gypsy.neatorganizer.data.model.tasks

import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity

data class SingleTaskGroupWithTasks(
    val taskGroup: SingleTaskGroup,
    val tasks: List<SingleTaskEntry> = emptyList(),
)

internal fun SingleTaskGroupWithTasks.toSingleTaskGroupEntity() = SingleTaskGroupEntity(
    name = this.taskGroup.name,
    id = this.taskGroup.id,
    createdAt = this.taskGroup.createdAt
)
