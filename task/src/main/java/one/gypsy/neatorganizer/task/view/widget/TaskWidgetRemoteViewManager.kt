package one.gypsy.neatorganizer.task.view.widget

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
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import one.gypsy.neatorganizer.core.widget.WidgetRemoteViewManager
import one.gypsy.neatorganizer.domain.dto.tasks.TitledTaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.DeleteTaskWidgetById
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTitledTaskWidget
import one.gypsy.neatorganizer.task.R
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.MANAGED_GROUP_ID_KEY
import one.gypsy.neatorganizer.task.view.widget.TaskWidgetKeyring.MANAGED_GROUP_INVALID_ID

class TaskWidgetRemoteViewManager(
    private val context: Context,
    private val widgetManager: AppWidgetManager,
    private val loadTitledTaskWidgetUseCase: LoadTitledTaskWidget,
    private val removeTaskWidgetUseCase: DeleteTaskWidgetById
) : WidgetRemoteViewManager {

    override fun updateWidget(appWidgetId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            loadTitledTaskWidgetUseCase.invoke(this, LoadTitledTaskWidget.Params(appWidgetId)) {
                it.either(
                    { onLoadTaskWidgetFailure(appWidgetId) },
                    ::onLoadTaskWidgetSuccess
                )
            }
        }
    }

    private fun onLoadTaskWidgetFailure(appWidgetId: Int) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_no_content).apply {
            setUpMissingGroupViews(appWidgetId)
        }
        widgetManager.updateAppWidget(appWidgetId, remoteViews)
    }

    // TODO it uses directly domain objects turn it into model objects
    private fun onLoadTaskWidgetSuccess(taskWidgetEntry: TitledTaskWidgetEntry) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasks).apply {
            setUpLoadedTaskViews(taskWidgetEntry)
        }
        widgetManager.updateAppWidget(taskWidgetEntry.appWidgetId, remoteViews)
        // TODO whole widget gets updated at once, try to split the process only to necessary operations
        widgetManager.notifyAppWidgetViewDataChanged(taskWidgetEntry.appWidgetId, R.id.tasksList)
    }

    private fun RemoteViews.setUpLoadedTaskViews(taskWidgetEntry: TitledTaskWidgetEntry) {
        setOnClickPendingIntent(
            R.id.widgetContainer,
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

    private fun RemoteViews.setUpMissingGroupViews(widgetId: Int) {
        setOnClickPendingIntent(
            R.id.widgetContainer,
            createGroupManageActivityIntent(
                widgetId,
                MANAGED_GROUP_INVALID_ID
            )
        )
        setTextViewText(
            R.id.noContentMessage,
            context.getString(R.string.task_widget_missing_content_message)
        )
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
            removeTaskWidgetUseCase.invoke(this, DeleteTaskWidgetById.Params(appWidgetId))
        }
    }
}
