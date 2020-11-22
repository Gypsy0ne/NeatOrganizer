package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import one.gypsy.neatorganizer.presentation.common.WidgetNotifier


class TaskWidgetNotifier(private val context: Context) : WidgetNotifier {

    override fun sendUpdateWidgetBroadcast(widgetIds: IntArray) =
        context.sendBroadcast(createUpdateIntent(widgetIds))

    private fun createUpdateIntent(updatedWidgetsIds: IntArray) =
        Intent(context, TasksAppWidget::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(TasksAppWidget.WIDGET_UPDATE_IDS_KEY, updatedWidgetsIds)
        }

}