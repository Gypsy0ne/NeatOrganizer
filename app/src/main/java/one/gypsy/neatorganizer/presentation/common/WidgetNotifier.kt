package one.gypsy.neatorganizer.presentation.common

interface WidgetNotifier {
    fun sendUpdateWidgetBroadcast(widgetIds: IntArray)
}