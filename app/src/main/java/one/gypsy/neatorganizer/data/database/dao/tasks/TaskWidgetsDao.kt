package one.gypsy.neatorganizer.data.database.dao.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import one.gypsy.neatorganizer.data.database.dao.BaseDao
import one.gypsy.neatorganizer.data.database.entity.tasks.TaskWidgetEntity
import one.gypsy.neatorganizer.data.database.entity.tasks.WidgetAndTaskGroup

@Dao
interface TaskWidgetsDao : BaseDao<TaskWidgetEntity> {

    @Query("SELECT * FROM task_widgets")
    fun getAllTaskWidgets(): List<TaskWidgetEntity>

    @Query("SELECT * FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun getWidgetById(taskWidgetId: Int): TaskWidgetEntity

    @Query("SELECT * FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun getWidgetWithTaskGroupById(taskWidgetId: Int): WidgetAndTaskGroup

    @Query("SELECT * FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun getWidgetWithTaskGroupByIdObservable(taskWidgetId: Int): LiveData<WidgetAndTaskGroup>

    @Query("SELECT widgetId FROM task_widgets")
    fun getAllWidgetIds(): IntArray

    @Query("DELETE FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun deleteWidgetById(taskWidgetId: Int)

    @Query("UPDATE task_widgets SET taskGroupId = :taskGroupId WHERE widgetId = :taskWidgetId")
    fun updateLinkedTaskGroupById(taskWidgetId: Int, taskGroupId: Long)

    @Query("SELECT * FROM task_widgets")
    fun getAllTaskWidgetsObservable(): LiveData<List<TaskWidgetEntity>>

    @Query("SELECT taskGroupId FROM task_widgets WHERE widgetId = :taskWidgetId")
    fun getTaskGroupIdByWidgetId(taskWidgetId: Int): Long
}