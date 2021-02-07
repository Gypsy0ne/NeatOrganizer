package one.gypsy.neatorganizer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import one.gypsy.neatorganizer.presentation.routines.alarm.RoutinesResetManager
import org.koin.core.KoinComponent
import org.koin.core.inject

class LaunchActivity : AppCompatActivity(), KoinComponent {

    private val routinesResetManager: RoutinesResetManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableRoutinesReset()
        redirectToHomeActivity()
    }

    private fun redirectToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun enableRoutinesReset() = routinesResetManager.scheduleRoutinesResetWork()
}
