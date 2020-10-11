package one.gypsy.neatorganizer.presentation.common

import android.appwidget.AppWidgetManager
import android.content.Context

interface WidgetRemoteViewManager {
    fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    )

    fun deleteWidget(context: Context, appWidgetId: Int)
}