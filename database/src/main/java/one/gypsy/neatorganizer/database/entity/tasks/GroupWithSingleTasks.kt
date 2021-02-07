package one.gypsy.neatorganizer.database.entity.tasks

import androidx.room.Embedded
import androidx.room.Relation

data class GroupWithSingleTasks(
    @Embedded val group: SingleTaskGroupEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    ) val tasks: List<SingleTaskEntity>
)
