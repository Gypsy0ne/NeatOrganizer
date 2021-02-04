package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.database.entity.tasks.SingleTaskGroupEntity
import one.gypsy.neatorganizer.utils.Timestamped

data class SingleTaskGroup(
    val name: String,
    var id: Long = 0,
    override val createdAt: Long
) : Timestamped

fun SingleTaskGroup.toSingleTaskGroupEntity() =
    SingleTaskGroupEntity(
        name = this.name,
        id = this.id,
        createdAt = this.createdAt
    )
