package one.gypsy.neatorganizer.routine.alarm

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import one.gypsy.neatorganizer.domain.interactors.routines.RunAllRoutinesSnapshotReset
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

class RoutinesResetWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters), CoroutineScope, KoinComponent {

    private val resetRoutineTasksUseCase: RunAllRoutinesSnapshotReset by inject()
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    override fun doWork(): Result {
        resetRoutineTasksUseCase.invoke(this, Unit)
        return Result.success()
    }

    companion object {
        const val ROUTINES_RESET_KEY = "RoutinesResetWorker"
    }
}
