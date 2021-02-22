package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.model.tasks.SingleTaskEntry

data class SingleTaskEntryDto(
    val id: Long = 0,
    val name: String,
    var done: Boolean,
    val groupId: Long,
    val createdAt: Long
)

fun SingleTaskEntryDto.toSingleTaskEntry() = SingleTaskEntry(
    id = this.id,
    done = this.done,
    groupId = this.groupId,
    name = this.name,
    createdAt = this.createdAt
)
