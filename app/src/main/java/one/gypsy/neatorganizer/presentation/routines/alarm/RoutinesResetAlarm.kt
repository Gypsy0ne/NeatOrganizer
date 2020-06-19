package one.gypsy.neatorganizer.presentation.routines.alarm

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_FIFTEEN_MINUTES
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.util.*


//https://stackoverflow.com/questions/4459058/alarm-manager-example
class RoutinesResetAlarm : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "elo", Toast.LENGTH_LONG).show()
    }

    fun setWeeklyRoutinesResetAlarm(context: Context) {
        val alarmIntent = Intent(context, RoutinesResetAlarm::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val wakeTime: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
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
