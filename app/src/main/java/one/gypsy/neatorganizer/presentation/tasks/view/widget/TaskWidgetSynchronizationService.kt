package one.gypsy.neatorganizer.presentation.tasks.view.widget

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import one.gypsy.neatorganizer.domain.dto.tasks.SingleTaskGroupWithTasks
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllSingleTaskGroups
import one.gypsy.neatorganizer.domain.interactors.tasks.GetAllTaskWidgetIds
import one.gypsy.neatorganizer.presentation.common.WidgetNotifier
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject

class TaskWidgetSynchronizationService : LifecycleService(), KoinComponent {

    private val getAllWidgetIdsUseCase: GetAllTaskWidgetIds by inject()
    private val widgetNotifier: WidgetNotifier by inject()
    private val getAllSingleTaskGroupsUseCase: GetAllSingleTaskGroups = get()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        getAllSingleTaskGroupsUseCase.invoke(lifecycleScope, Unit) {
            it.either({
                stopSelf()
            }, ::onGetAllSingleTaskGroupsSuccess)
        }
        return START_STICKY
    }

    //for now livedata invokes flow every time it is used and for every widget
    //TODO diff to update only particular widget and only when it is needed
    //TODO if some task group gets deleted remove widget
    private fun onGetAllSingleTaskGroupsSuccess(taskGroupsWithTasks: LiveData<List<SingleTaskGroupWithTasks>>) =
        taskGroupsWithTasks.observe(this, Observer {
            getAllWidgetIdsUseCase.invoke(lifecycleScope, Unit) {
                it.either({}, ::onGetAllWidgetIdsSuccess)
            }
        })

    private fun onGetAllWidgetIdsSuccess(taskWidgetIds: IntArray) {
        widgetNotifier.sendUpdateWidgetBroadcast(taskWidgetIds)
    }
}

