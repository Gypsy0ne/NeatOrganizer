package one.gypsy.neatorganizer.task.view.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import one.gypsy.neatorganizer.core.widget.WidgetNotifier

class TaskWidgetNotifier(private val context: Context) : WidgetNotifier {

    override fun sendUpdateWidgetBroadcast(widgetIds: IntArray) =
        context.sendBroadcast(createUpdateIntent(widgetIds))

    private fun createUpdateIntent(updatedWidgetsIds: IntArray) =
        Intent(context, TasksAppWidget::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(TasksAppWidget.TASK_WIDGET_UPDATE_IDS_KEY, updatedWidgetsIds)
        }
}
