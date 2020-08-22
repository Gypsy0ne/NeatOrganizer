package one.gypsy.neatorganizer.presentation.routines.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.text.SimpleDateFormat
import java.util.*

class RoutinesResetAutoStart : BroadcastReceiver(), KoinComponent {
    private val routinesResetAlarm: RoutinesResetAlarm by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent?.action == "android.intent.action.BOOT_COMPLETED") {
            Log.i(
                "RoutinesResetAutoStart",
                "RoutinesReset set attempt at " + SimpleDateFormat().format(Date())
            )
            routinesResetAlarm.setWeeklyRoutinesResetAlarm(context)
        }
    }

}
