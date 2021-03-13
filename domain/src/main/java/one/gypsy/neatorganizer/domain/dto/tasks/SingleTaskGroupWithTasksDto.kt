package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroup
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroupWithTasks

data class SingleTaskGroupWithTasksDto(
    val taskGroup: SingleTaskGroupDto,
    val tasks: List<SingleTaskEntryDto> = emptyList(),
)

internal fun SingleTaskGroupWithTasksDto.toSingleTaskGroup() = SingleTaskGroup(
    id = this.taskGroup.id,
    name = this.taskGroup.name,
    createdAt = this.taskGroup.createdAt
)

internal fun SingleTaskGroupWithTasksDto.toSingleTaskGroupWithTasks() = SingleTaskGroupWithTasks(
    taskGroup = taskGroup.toSingleTaskGroup(),
    tasks = tasks.map { it.toSingleTaskEntry() }
)
