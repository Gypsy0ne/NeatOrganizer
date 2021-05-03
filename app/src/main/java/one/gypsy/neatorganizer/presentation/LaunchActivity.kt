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
        navigateHomeWithRoutineTasksReset()
    }

    private fun navigateHomeWithRoutineTasksReset() {
        routinesResetManager.resetRoutineTasks(lifecycleScope) {
            redirectToHomeActivity()
        }
    }

    private fun redirectToHomeActivity() = Intent(this, HomeActivity::class.java).let {
        startActivity(it)
        finish()
    }
}
