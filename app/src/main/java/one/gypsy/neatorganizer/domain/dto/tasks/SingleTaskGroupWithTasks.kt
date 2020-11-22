package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity

data class SingleTaskGroupWithTasks(
    val name: String,
    var id: Long = 0,
    var tasks: List<SingleTaskEntry> = emptyList()
)

fun SingleTaskGroupWithTasks.toSingleTaskGroupEntity() =
    SingleTaskGroupEntity(name = this.name, id = this.id)