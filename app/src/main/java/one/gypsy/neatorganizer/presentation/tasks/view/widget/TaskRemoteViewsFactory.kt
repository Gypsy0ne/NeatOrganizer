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
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTasksByGroupId
import one.gypsy.neatorganizer.domain.interactors.tasks.LoadTaskWidget
import one.gypsy.neatorganizer.presentation.tasks.model.TaskEntryWidgetItem
import one.gypsy.neatorganizer.presentation.tasks.model.toTaskEntryWidgetItem
import org.koin.core.KoinComponent
import org.koin.core.inject


//odpowiedzialnosci: mapownaie itemow, sciaganie itemow, tworzenie widokow
class TaskRemoteViewsFactory(private val context: Context, intent: Intent) :
    RemoteViewsService.RemoteViewsFactory, KoinComponent {

    private val loadTaskWidgetUseCase: LoadTaskWidget by inject()
    private val getAllSingleTasksUseCase: GetAllSingleTasksByGroupId by inject()
    private val widgetItems = arrayListOf<TaskEntryWidgetItem>(TaskEntryWidgetItem("siema", true))
    private val appWidgetId: Int = intent.getIntExtra(
        AppWidgetManager.EXTRA_APPWIDGET_ID,
        AppWidgetManager.INVALID_APPWIDGET_ID
    )
    private var taskGroupId: Long? = null

    override fun onCreate() = runBlocking {
        loadTaskWidgetUseCase.invoke(this, LoadTaskWidget.Params(appWidgetId)) {
            it.either({}, ::onLoadTaskWidgetSuccess)
        }
    }

    private fun onLoadTaskWidgetSuccess(widgetEntry: TaskWidgetEntry) {
        taskGroupId = widgetEntry.taskGroupId
    }

    override fun onDataSetChanged() {
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

    // Next, we set a fill-intent which will be used to fill-in the pending intent template
    // which is set on the collection view in StackWidgetProvider.
    // Next, we set a fill-intent which will be used to fill-in the pending intent template
    // which is set on the collection view in StackWidgetProvider.
//        val extras = Bundle().apply {
//            putInt(StackWidgetProvider.EXTRA_ITEM, position)
//        }
//        val fillInIntent = Intent().apply {
//            putExtras(extras)
//        }
//        remoteViews.setOnClickFillInIntent(R.id.widget_item, fillInIntent)

    private fun RemoteViews.styleTaskTextView(widgetItem: TaskEntryWidgetItem) {
        if (widgetItem.done) {
            setInt(
                R.id.taskText,
                "setPaintFlags",
                Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            )
        }
        setTextViewText(R.id.taskText, widgetItem.text)
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}