package one.gypsy.neatorganizer.presentation.routines.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


//https://stackoverflow.com/questions/4459058/alarm-manager-example
class RoutineBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            // Set the alarm here.
        }
    }
}
