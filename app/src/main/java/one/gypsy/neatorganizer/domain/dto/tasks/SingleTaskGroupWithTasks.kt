package one.gypsy.neatorganizer.domain.dto.tasks

import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.data.database.entity.tasks.SingleTaskGroupEntity
// TODO make it as composition object which holds Entries and Group in one place
data class SingleTaskGroupWithTasks(
    val name: String,
    var id: Long = 0,
    var tasks: List<SingleTaskEntry> = emptyList(),
    override val createdAt: Long
) : Timestamped

fun SingleTaskGroupWithTasks.toSingleTaskGroupEntity() =
    SingleTaskGroupEntity(name = this.name, id = this.id, createdAt = this.createdAt)
