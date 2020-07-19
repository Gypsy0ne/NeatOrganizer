package one.gypsy.neatorganizer.presentation.routines.alarm

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_FIFTEEN_MINUTES
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.*
import kotlin.coroutines.CoroutineContext

class RoutinesResetAlarm : CoroutineScope {
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

//    @Inject
//    lateinit var resetRoutineTasksUseCase: ResetAllRoutineTasks

    fun setWeeklyRoutinesResetAlarm(context: Context) {
        val alarmIntent = Intent(context, RoutinesResetAlarm::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val wakeTime: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
            set(Calendar.HOUR_OF_DAY, 17)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            wakeTime.timeInMillis,
            INTERVAL_FIFTEEN_MINUTES / 30,
            alarmIntent
        )
    }

}
