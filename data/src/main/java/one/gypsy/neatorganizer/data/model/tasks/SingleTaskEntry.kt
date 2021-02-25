package one.gypsy.neatorganizer.data.model.tasks

import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity

data class SingleTaskEntry(
    val id: Long = 0,
    val name: String,
    var done: Boolean,
    val groupId: Long,
    val createdAt: Long
)

internal fun SingleTaskEntry.toSingleTaskEntity() = SingleTaskEntity(
    id = this.id,
    done = this.done,
    groupId = this.groupId,
    name = this.name,
    createdAt = this.createdAt
)
