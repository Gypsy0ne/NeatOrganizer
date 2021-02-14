package one.gypsy.neatorganizer.routine.alarm

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

class RoutinesResetManager(context: Context) {

    private val workManager = WorkManager.getInstance(context)

    fun scheduleRoutinesResetWork() {
        workManager.enqueueUniquePeriodicWork(
            RoutinesResetWorker.ROUTINES_RESET_KEY,
            ExistingPeriodicWorkPolicy.KEEP,
            createResetWorkRequest()
        )
    }

    private fun createResetWorkRequest(): PeriodicWorkRequest =
        PeriodicWorkRequest.Builder(RoutinesResetWorker::class.java, 7, TimeUnit.DAYS)
            .setInitialDelay(
                getInitialDelayToNextGivenDay(Calendar.MONDAY), TimeUnit.MILLISECONDS
            ).build()

    private fun getInitialDelayToNextGivenDay(calendarDay: Int): Long {
        val initialDate: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, calendarDay)
            set(Calendar.HOUR_OF_DAY, 1)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val now = Calendar.getInstance()
        now.set(Calendar.SECOND, 0)
        now.set(Calendar.MILLISECOND, 0)

        if (initialDate.before(now)) {
            initialDate.add(Calendar.DATE, 7)
        }

        return initialDate.timeInMillis - now.timeInMillis
    }
}
