package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity

data class SingleTaskGroup(
    val name: String,
    var id: Long = 0,
    var tasks: List<SingleTaskEntry> = emptyList()
)

fun SingleTaskGroup.toSingleTaskGroupEntity() =
    SingleTaskGroupEntity(name = this.name, id = this.id)