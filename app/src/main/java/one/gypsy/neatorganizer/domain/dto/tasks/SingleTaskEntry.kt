package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskEntity
import one.gypsy.neatorganizer.utils.Timestamped

data class SingleTaskEntry(
    val id: Long = 0,
    val name: String,
    var done: Boolean,
    val groupId: Long,
    override val createdAt: Long
) : Timestamped

fun SingleTaskEntry.toSingleTaskEntity() =
    SingleTaskEntity(
        id = this.id,
        done = this.done,
        groupId = this.groupId,
        name = this.name,
        createdAt = this.createdAt
    )
