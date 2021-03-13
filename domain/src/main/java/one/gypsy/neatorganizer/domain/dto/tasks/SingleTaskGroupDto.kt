package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.SingleTaskGroup

data class SingleTaskGroupDto(
    val name: String,
    var id: Long = 0,
    val createdAt: Long
)

internal fun SingleTaskGroupDto.toSingleTaskGroup() = SingleTaskGroup(
    name = this.name,
    id = this.id,
    createdAt = this.createdAt
)
