package one.gypsy.neatorganizer.presentation

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetAutoStart
import javax.inject.Inject


class LaunchActivity : AppCompatActivity(), HasAndroidInjector {

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        enableRoutinesReset()
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
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}