package one.gypsy.neatorganizer.domain.datasource.tasks

import android.content.SharedPreferences
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.utils.INVALID_COLOR_CODE
import one.gypsy.neatorganizer.utils.INVALID_TASK_ITEM
import one.gypsy.neatorganizer.utils.PREF_COLOR_KEY
import one.gypsy.neatorganizer.utils.PREF_TASK_GROUP_KEY

class UserTaskWidgetDataSource(private val sharedPreferences: SharedPreferences) :
    TaskWidgetDataSource {
    override suspend fun save(taskWidgetEntry: TaskWidgetEntry) {
        with(sharedPreferences.edit()) {
            putInt(PREF_COLOR_KEY + taskWidgetEntry.appWidgetId, taskWidgetEntry.widgetColor)
            putLong(PREF_TASK_GROUP_KEY + taskWidgetEntry.appWidgetId, taskWidgetEntry.taskGroupId)
            apply()
        }
    }

    override suspend fun load(taskWidgetId: Int): TaskWidgetEntry = with(sharedPreferences) {
        TaskWidgetEntry(
            taskWidgetId,
            getLong(PREF_TASK_GROUP_KEY + taskWidgetId, INVALID_TASK_ITEM),
            getInt(PREF_COLOR_KEY + taskWidgetId, INVALID_COLOR_CODE)
        )
    }

    override suspend fun delete(taskWidgetId: Int) {
        TODO("Not yet implemented")
    }

}