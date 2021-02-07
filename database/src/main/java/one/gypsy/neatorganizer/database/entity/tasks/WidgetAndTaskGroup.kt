package one.gypsy.neatorganizer.database.entity.tasks

import androidx.room.Embedded
import androidx.room.Relation

class WidgetAndTaskGroup(
    @Relation(
        parentColumn = "taskGroupId",
        entityColumn = "id"
    )
    val singleTaskGroup: SingleTaskGroupEntity,
    @Embedded
    val widget: TaskWidgetEntity
)
