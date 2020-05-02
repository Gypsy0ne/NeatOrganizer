package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskEntity

data class SingleTaskEntry(
    val id: Long = 0,
    val name: String,
    var done: Boolean,
    val groupId: Long
)

fun SingleTaskEntry.toSingleTaskEntity() =
    SingleTaskEntity(id = this.id, done = this.done, groupId = this.groupId, name = this.name)