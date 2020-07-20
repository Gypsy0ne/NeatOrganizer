package one.gypsy.neatorganizer.presentation.routines.alarm

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_FIFTEEN_MINUTES
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
        val alarmIntent = Intent(context, RoutinesResetAlarm::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val wakeTime: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            set(Calendar.HOUR_OF_DAY, 24)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            wakeTime.timeInMillis,
            INTERVAL_FIFTEEN_MINUTES / 30,
            alarmIntent
        )
    }
}
