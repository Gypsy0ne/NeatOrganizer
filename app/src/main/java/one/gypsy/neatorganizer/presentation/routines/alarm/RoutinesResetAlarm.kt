package one.gypsy.neatorganizer.presentation.routines.alarm

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import one.gypsy.neatorganizer.domain.interactors.routines.ResetAllRoutineTasks
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*
import kotlin.coroutines.CoroutineContext

class RoutinesResetAlarm : BroadcastReceiver(), CoroutineScope, KoinComponent {

    private val resetRoutineTasksUseCase: ResetAllRoutineTasks by inject()
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    override fun onReceive(context: Context, intent: Intent) {
        resetRoutineTasksUseCase.invoke(this, Unit)
    }

    fun setWeeklyRoutinesResetAlarm(context: Context) {
        if (!isAlarmUp(context)) {
            val alarmIntent = Intent(context, RoutinesResetAlarm::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, 0)
            }

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                getWakeTimeMillis(),
                INTERVAL_DAY * 7,
                alarmIntent
            )
        }
    }

    private fun isAlarmUp(context: Context) =
        PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, RoutinesResetAlarm::class.java),
            PendingIntent.FLAG_NO_CREATE
        ) != null

    private fun getWakeTimeMillis(): Long {
        val wakeTime: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            set(Calendar.HOUR_OF_DAY, 24)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val now = Calendar.getInstance()
        now.set(Calendar.SECOND, 0)
        now.set(Calendar.MILLISECOND, 0)

        if (wakeTime.before(now)) {
            wakeTime.add(Calendar.DATE, 7)
        }

        return wakeTime.timeInMillis
    }
}
