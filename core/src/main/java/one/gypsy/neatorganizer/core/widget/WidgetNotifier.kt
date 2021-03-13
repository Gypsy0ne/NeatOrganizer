package one.gypsy.neatorganizer.core.widget

interface WidgetNotifier {
    fun sendUpdateWidgetBroadcast(widgetIds: IntArray)
}
