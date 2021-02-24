package one.gypsy.neatorganizer.task.view.widget

import android.content.Intent
import android.widget.RemoteViewsService

internal class TaskWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        requireNotNull(intent) {
            "Widget intent cannot be null"
        }
        return TaskRemoteViewsFactory(applicationContext, intent)
    }
}
