package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.TaskWidgetEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.WidgetAndTaskGroup

@Dao
interface TaskWidgetsDao : BaseDao<TaskWidgetEntity> {
    @Query("SELECT * FROM TASK_WIDGETS WHERE widgetId = :taskWidgetId")
    fun getWidgetWithTaskGroupById(taskWidgetId: Int): WidgetAndTaskGroup
}