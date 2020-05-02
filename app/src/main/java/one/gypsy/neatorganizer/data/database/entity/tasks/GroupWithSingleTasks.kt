package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroup

data class GroupWithSingleTasks(
    @Embedded val group: SingleTaskGroupEntity, @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    ) val tasks: List<SingleTaskEntity>
)

fun GroupWithSingleTasks.toSingleTaskGroup() = SingleTaskGroup(
    name = this.group.name,
    id = this.group.id,
    tasks = this.tasks.map { it.toSingleTaskEntry() }
)