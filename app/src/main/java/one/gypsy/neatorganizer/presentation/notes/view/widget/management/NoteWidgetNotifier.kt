package one.gypsy.neatorganizer.presentation.notes.view.widget.management

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import one.gypsy.neatorganizer.presentation.common.WidgetNotifier
import one.gypsy.neatorganizer.presentation.notes.view.widget.remote.NotesAppWidget

class NoteWidgetNotifier(private val context: Context) : WidgetNotifier {

    override fun sendUpdateWidgetBroadcast(widgetIds: IntArray) =
        context.sendBroadcast(createUpdateIntent(widgetIds))

    private fun createUpdateIntent(updatedWidgetsIds: IntArray) =
        Intent(context, NotesAppWidget::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(NotesAppWidget.NOTE_WIDGET_UPDATE_IDS_KEY, updatedWidgetsIds)
        }
}
