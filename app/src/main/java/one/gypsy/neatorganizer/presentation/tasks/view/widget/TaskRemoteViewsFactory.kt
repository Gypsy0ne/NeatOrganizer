package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import kotlinx.coroutines.runBlocking
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupId
import one.gypsy.neatorganizer.domain.interactors.tasks.GetTaskGroupIdByWidgetId
import one.gypsy.neatorganizer.presentation.tasks.model.TaskEntryWidgetItem
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskEntryWidgetItem
import org.koin.core.KoinComponent
import org.koin.core.inject

//odpowiedzialnosci: mapownaie itemow, sciaganie itemow, tworzenie widokow
class TaskRemoteViewsFactory(private val context: Context, intent: Intent) :
    RemoteViewsService.RemoteViewsFactory, KoinComponent {

    private val getTaskGroupIdByWidgetIdUseCase: GetTaskGroupIdByWidgetId by inject()
    private val getAllSingleTasksUseCase: GetAllSingleTasksByGroupId by inject()
    private val widgetItems = arrayListOf<TaskEntryWidgetItem>()
    private val appWidgetId: Int = intent.getIntExtra(
        AppWidgetManager.EXTRA_APPWIDGET_ID,
        AppWidgetManager.INVALID_APPWIDGET_ID
    )
    private var taskGroupId: Long? = null

    override fun onCreate() = loadTaskGroupId()

    private fun loadTaskGroupId() = runBlocking {
        getTaskGroupIdByWidgetIdUseCase.invoke(this, GetTaskGroupIdByWidgetId.Params(appWidgetId)) {
            it.either({}, ::loadTaskGroupIdSuccess)
        }
    }

    private fun loadTaskGroupIdSuccess(loadedTaskGroupId: Long) {
        taskGroupId = loadedTaskGroupId
    }

    override fun onDataSetChanged() {
        loadTaskGroupId()
        taskGroupId?.let {
            runBlocking {
                getAllSingleTasksUseCase.invoke(this, GetAllSingleTasksByGroupId.Params(it)) {
                    it.either({}, ::onGetAllSingleTasksSuccess)
                }
            }
        }
    }

    private fun onGetAllSingleTasksSuccess(singleTasks: List<SingleTaskEntry>) {
        widgetItems.clear()
        widgetItems.addAll(singleTasks.map { it.toTaskEntryWidgetItem() })
    }

    override fun onDestroy() = widgetItems.clear()

    override fun getCount(): Int = widgetItems.size

    override fun getViewAt(position: Int): RemoteViews =
        RemoteViews(context.packageName, R.layout.widget_item_task).apply {
            styleTaskTextView(widgetItems[position])
        }

    private fun RemoteViews.styleTaskTextView(widgetItem: TaskEntryWidgetItem) {
        val paintFlags = getPaintFlags(widgetItem.done)
        setInt(R.id.taskText, "setPaintFlags", paintFlags)
        setTextViewText(R.id.taskText, widgetItem.text)
    }

    private fun getPaintFlags(done: Boolean): Int = if (done) {
        Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
    } else {
        Paint.STRIKE_THRU_TEXT_FLAG.inv() and Paint.ANTI_ALIAS_FLAG
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}