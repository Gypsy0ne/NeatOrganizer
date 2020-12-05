package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupEntry
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks

data class GroupWithSingleTasks(
    @Embedded val group: SingleTaskGroupEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    ) val tasks: List<SingleTaskEntity>
)

fun GroupWithSingleTasks.toSingleTaskGroupWithTasks() = SingleTaskGroupWithTasks(
    name = this.group.name,
    id = this.group.id,
    tasks = this.tasks.map { it.toSingleTaskEntry() },
    createdAt = group.createdAt
)

fun GroupWithSingleTasks.toSingleTaskGroupEntry() = SingleTaskGroupEntry(
    group.id,
    group.name,
    tasksDone = tasks.count { it.done },
    tasksCount = tasks.count()
)
