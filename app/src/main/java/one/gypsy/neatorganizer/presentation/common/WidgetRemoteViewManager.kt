package one.gypsy.neatorganizer.presentation.common

interface WidgetRemoteViewManager {
    fun updateWidget(appWidgetId: Int)
    fun deleteWidget(appWidgetId: Int)
}