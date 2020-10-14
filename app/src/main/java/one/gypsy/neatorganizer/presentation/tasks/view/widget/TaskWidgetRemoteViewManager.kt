package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTaskWidget
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager

class TaskWidgetRemoteViewManager(private val loadTaskWidgetUseCase: LoadTaskWidget) :
    WidgetRemoteViewManager {
    override fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            loadTaskWidgetUseCase.invoke(this, LoadTaskWidget.Params(appWidgetId)) {
                it.either(
                    {},
                    { taskWidgetEntry ->
                        onLoadTaskWidgetSuccess(
                            context,
                            appWidgetManager,
                            taskWidgetEntry
                        )
                    })
            }
        }
    }

    private fun onLoadTaskWidgetSuccess(
        context: Context,
        appWidgetManager: AppWidgetManager,
        taskWidgetEntry: TaskWidgetEntry
    ) {
        val intent = Intent(context, TaskWidgetService::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, taskWidgetEntry.appWidgetId)
            data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
        }
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasks).apply {
            setRemoteAdapter(R.id.tasksList, intent)
            setEmptyView(R.id.tasksList, R.id.emptyView)
        }
        appWidgetManager.updateAppWidget(taskWidgetEntry.appWidgetId, remoteViews)
    }

    override fun deleteWidget(appWidgetId: Int) {
        TODO("Not yet implemented")
    }

}