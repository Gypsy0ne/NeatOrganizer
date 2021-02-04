package one.gypsy.neatorganizer.database.entity.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_widgets")
data class TaskWidgetEntity(
    @PrimaryKey val widgetId: Int,
    val taskGroupId: Long,
    val color: Int
)
