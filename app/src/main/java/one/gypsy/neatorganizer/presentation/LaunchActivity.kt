package one.gypsy.neatorganizer.presentation

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAlarm
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAutoStart
import org.koin.core.KoinComponent
import org.koin.core.inject

class LaunchActivity : AppCompatActivity(), KoinComponent {

    private val routinesReset: RoutinesResetAlarm by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableRoutinesResetAutoStart()
        enableRoutinesReset()
        redirectToHomeActivity()
    }

    private fun redirectToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun enableRoutinesReset() =
        routinesReset.setWeeklyRoutinesResetAlarm(applicationContext)

    private fun enableRoutinesResetAutoStart() {
        val receiver = ComponentName(applicationContext, RoutinesResetAutoStart::class.java)
        applicationContext.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}