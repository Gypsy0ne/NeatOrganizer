package one.gypsy.neatorganizer.presentation

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAutoStart


class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableRoutinesReset()
        redirectToHomeActivity()
    }

    private fun redirectToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun enableRoutinesReset() {
        val receiver = ComponentName(applicationContext, RoutinesResetAutoStart::class.java)
        applicationContext.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}