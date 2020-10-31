package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import org.koin.core.KoinComponent
import org.koin.core.inject


class TasksAppWidget : AppWidgetProvider(), KoinComponent {
    private val widgetViewManager: WidgetRemoteViewManager by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.hasExtra(WIDGET_UPDATE_IDS_KEY) == true) {
            context?.let {
                onUpdate(
                    it, AppWidgetManager.getInstance(it), intent.getIdsArray()
                )
            }
        } else super.onReceive(context, intent)
    }

    private fun Intent.getIdsArray() = getIntArrayExtra(WIDGET_UPDATE_IDS_KEY) ?: intArrayOf()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            widgetViewManager.updateWidget(appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            widgetViewManager.deleteWidget(appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        const val WIDGET_UPDATE_IDS_KEY = "updatedTaskWidgetIds"
        const val WIDGET_DELETE_IDS_KEY = "updatedTaskWidgetIds"
    }
}
