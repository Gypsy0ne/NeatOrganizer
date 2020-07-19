package one.gypsy.neatorganizer.presentation.routines.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RoutinesResetAutoStart(private val routinesResetAlarm: RoutinesResetAlarm) :
    BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent?.action == "android.intent.action.BOOT_COMPLETED") {
            routinesResetAlarm.setWeeklyRoutinesResetAlarm(context)
        }
    }

}
