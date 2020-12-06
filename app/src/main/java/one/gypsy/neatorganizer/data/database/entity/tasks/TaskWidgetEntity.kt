package one.gypsy.neatorganizer.data.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry

@Entity(tableName = "task_widgets")
data class TaskWidgetEntity(
    @PrimaryKey val widgetId: Int,
    val taskGroupId: Long,
    val color: Int
)

fun TaskWidgetEntity.toTaskWidgetEntry() =
    TaskWidgetEntry(appWidgetId = widgetId, taskGroupId = taskGroupId, widgetColor = color)
