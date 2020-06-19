package one.gypsy.neatorganizer.presentation.routines.alarm

import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver


class RoutinesResetAutoStart : DaggerBroadcastReceiver() {
    //TODO inject it
    val routinesResetAlarm: RoutinesResetAlarm = RoutinesResetAlarm()

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            routinesResetAlarm.setWeeklyRoutinesResetAlarm(context)
        }
    }
}
