package one.gypsy.neatorganizer.presentation.notes.view.widget.remote

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

class NotesAppWidget : AppWidgetProvider(), KoinComponent {

    private val widgetViewManager: WidgetRemoteViewManager by inject(named("noteRemoteViewManager"))

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.hasExtra(NOTE_WIDGET_UPDATE_IDS_KEY) == true) {
            context?.let {
                onUpdate(
                    it, AppWidgetManager.getInstance(it), intent.getIdsArray()
                )
            }
        } else super.onReceive(context, intent)
    }

    private fun Intent.getIdsArray() = getIntArrayExtra(NOTE_WIDGET_UPDATE_IDS_KEY) ?: intArrayOf()

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

    override fun onEnabled(context: Context) {}

    override fun onDisabled(context: Context) {}

    companion object {
        const val NOTE_WIDGET_UPDATE_IDS_KEY = "updatedTaskWidgetIds"
    }
}
