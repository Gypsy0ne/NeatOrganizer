package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroup

data class SingleTaskGroupWithTasksDto(
    val taskGroup: SingleTaskGroup,
    val tasks: List<SingleTaskEntry> = emptyList(),
)

fun SingleTaskGroupWithTasksDto.toSingleTaskGroup() = SingleTaskGroup(
    id = this.taskGroup.id,
    name = this.taskGroup.name,
    createdAt = this.taskGroup.createdAt
)
