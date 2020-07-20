package one.gypsy.neatorganizer.presentation.routines.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.KoinComponent
import org.koin.core.inject

class RoutinesResetAutoStart : BroadcastReceiver(), KoinComponent {
    private val routinesResetAlarm: RoutinesResetAlarm by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent?.action == "android.intent.action.BOOT_COMPLETED") {
            routinesResetAlarm.setWeeklyRoutinesResetAlarm(context)
        }
    }

}
