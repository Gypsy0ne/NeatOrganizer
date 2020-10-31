package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.TaskWidgetEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.WidgetAndTaskGroup

@Dao
interface TaskWidgetsDao : BaseDao<TaskWidgetEntity> {
    @Query("SELECT * FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun getWidgetWithTaskGroupById(taskWidgetId: Int): WidgetAndTaskGroup

    @Query("SELECT widgetId FROM task_widgets")
    fun getAllWidgetIds(): IntArray

    @Query("DELETE FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun deleteWidgetById(taskWidgetId: Int)
}