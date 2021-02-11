package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.dto.tasks.TaskWidgetEntry
import one.gypsy.neatorganizer.domain.tasks.GetAllSingleTaskGroups
import one.gypsy.neatorganizer.domain.tasks.GetAllTaskWidgetIds
import one.gypsy.neatorganizer.domain.tasks.GetAllTaskWidgets
import one.gypsy.neatorganizer.presentation.common.WidgetNotifier
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.qualifier.named

class TaskWidgetSynchronizationService : LifecycleService(), KoinComponent {

    private val getAllWidgetIdsUseCase: GetAllTaskWidgetIds by inject()
    private val widgetNotifier: WidgetNotifier by inject(named("taskWidgetNotifier"))
    private val getAllSingleTaskGroupsUseCase: GetAllSingleTaskGroups = get()
    private val getAllTaskWidgetsUseCase: GetAllTaskWidgets = get()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        getAllSingleTaskGroupsUseCase.invoke(lifecycleScope, Unit) {
            it.either(
                {
                    stopSelf()
                },
                ::onGetAllSingleTaskGroupsSuccess
            )
        }
        getAllTaskWidgetsUseCase.invoke(lifecycleScope, Unit) {
            it.either(
                {
                    stopSelf()
                },
                ::onGetAllTaskWidgetsSuccess
            )
        }
        return START_REDELIVER_INTENT
    }

    private fun onGetAllSingleTaskGroupsSuccess(taskGroupsWithTasks: LiveData<List<SingleTaskGroupWithTasks>>) =
        taskGroupsWithTasks.observe(
            this,
            {
                getAllWidgetIdsUseCase.invoke(lifecycleScope, Unit) {
                    it.either({}, ::updateTaskWidgets)
                }
            }
        )

    private fun updateTaskWidgets(taskWidgetIds: IntArray) {
        widgetNotifier.sendUpdateWidgetBroadcast(taskWidgetIds)
    }

    private fun onGetAllTaskWidgetsSuccess(taskWidgets: LiveData<List<TaskWidgetEntry>>) {
        taskWidgets.observe(this) {
            updateTaskWidgets(it.map { widget -> widget.appWidgetId }.toIntArray())
        }
    }
}
