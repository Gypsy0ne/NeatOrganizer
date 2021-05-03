package one.gypsy.neatorganizer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import one.gypsy.neatorganizer.routine.alarm.RoutinesResetManager
import org.koin.android.ext.android.inject

internal class LaunchActivity : AppCompatActivity() {

    private val routinesResetManager: RoutinesResetManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetRoutinesIfNeeded()
    }

    private fun resetRoutinesIfNeeded() {
        routinesResetManager.resetRoutineTasks(lifecycleScope) {
            redirectToHomeActivity()
        }
    }

    private fun redirectToHomeActivity() = Intent(this, HomeActivity::class.java).let {
        startActivity(it)
        finish()
    }
}
