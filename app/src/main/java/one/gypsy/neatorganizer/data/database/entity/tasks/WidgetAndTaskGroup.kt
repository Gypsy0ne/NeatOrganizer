package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Embedded
import androidx.room.Relation
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry

class WidgetAndTaskGroup(
    @Relation(
        parentColumn = "taskGroupId",
        entityColumn = "id"
    )
    val singleTaskGroup: SingleTaskGroupEntity,
    @Embedded
    val widget: TaskWidgetEntity
)

fun WidgetAndTaskGroup.toWidgetTaskEntry() = TaskWidgetEntry(
    appWidgetId = this.widget.widgetId,
    taskGroupId = this.widget.taskGroupId,
    widgetColor = this.widget.color,
    taskGroupTitle = this.singleTaskGroup.name
)