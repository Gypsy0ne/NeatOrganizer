package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.RemoteViews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.DeleteTaskWidget
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTaskWidget
import one.gypsy.neatorganizer.presentation.common.WidgetRemoteViewManager

class TaskWidgetRemoteViewManager(
    private val context: Context,
    private val widgetManager: AppWidgetManager,
    private val loadTaskWidgetUseCase: LoadTaskWidget,
    private val removeTaskWidgetUseCase: DeleteTaskWidget
) : WidgetRemoteViewManager {
    override fun updateWidget(appWidgetId: Int) {
//launch two async, gather data and then loadWidget
        //or store whole widget data in db and keep every field fresh with relations
        CoroutineScope(Dispatchers.IO).launch {
            loadTaskWidgetUseCase.invoke(this, LoadTaskWidget.Params(appWidgetId)) {
                it.either({}, { taskWidgetEntry -> onLoadTaskWidgetSuccess(taskWidgetEntry) })
            }
        }
    }

    private fun onLoadTaskWidgetSuccess(taskWidgetEntry: TaskWidgetEntry) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasks).apply {
            setUpViews(taskWidgetEntry)
        }
        widgetManager.updateAppWidget(taskWidgetEntry.appWidgetId, remoteViews)
        //TODO whole widget gets updated at once, try to split the process only to necessary operations
        widgetManager.notifyAppWidgetViewDataChanged(taskWidgetEntry.appWidgetId, R.id.tasksList)
    }

    private fun RemoteViews.setUpViews(taskWidgetEntry: TaskWidgetEntry) {
        setOnClickPendingIntent(
            R.id.tasksWidgetContainer,
            createGroupManageActivityIntent(
                taskWidgetEntry.appWidgetId,
                taskWidgetEntry.taskGroupId
            )
        )
        setRemoteAdapter(R.id.tasksList, createWidgetUpdateIntent(taskWidgetEntry.appWidgetId))
        setEmptyView(R.id.tasksList, R.id.emptyView)
        setInt(R.id.tasksList, "setBackgroundColor", taskWidgetEntry.widgetColor)
        setTextViewText(R.id.taskGroupTitle, taskWidgetEntry.taskGroupTitle)
        setTextColor(R.id.emptyView, taskWidgetEntry.widgetColor)
    }

    private fun createWidgetUpdateIntent(widgetId: Int) =
        Intent(context, TaskWidgetService::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
        }

    private fun createGroupManageActivityIntent(appwidgetId: Int, taskGroupId: Long) =
        PendingIntent.getActivity(
            context,
            appwidgetId,
            createManageActivityIntent(appwidgetId, taskGroupId),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

    private fun createManageActivityIntent(widgetId: Int, taskGroupId: Long) =
        Intent(context, TaskWidgetActivity::class.java).apply {
            putExtra(MANAGED_GROUP_ID_KEY, taskGroupId)
            putExtra(MANAGED_WIDGET_ID_KEY, widgetId)
            flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        }

    override fun deleteWidget(appWidgetId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            removeTaskWidgetUseCase.invoke(this, DeleteTaskWidget.Params(appWidgetId))
        }
    }
}