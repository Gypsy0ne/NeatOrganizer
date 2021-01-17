package one.gypsy.neatorganizer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.presentation.tasks.view.widget.TaskWidgetSynchronizationService
import one.gypsy.neatorganizer.utils.extensions.show
import one.gypsy.neatorganizer.utils.extensions.shrink

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpActionBar()
        startWidgetSynchronizationService()
        setUpLocationListener()
    }

    private fun setUpActionBar() {
        setSupportActionBar(organizerToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun startWidgetSynchronizationService() =
        Intent(this, TaskWidgetSynchronizationService::class.java).also { intent ->
            startService(intent)
        }

    override fun onStart() {
        super.onStart()
        setUpBottomNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, TaskWidgetSynchronizationService::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_activity_home_nav_container).navigateUp()
    }

    private fun setUpBottomNavigation() = bottom_navigation_view_activity_home
        .setupWithNavController(findNavController(R.id.fragment_activity_home_nav_container))

    private fun setUpLocationListener() {
        findNavController(R.id.fragment_activity_home_nav_container).addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.noteDetails) {
                bottom_navigation_view_activity_home.shrink()
            } else {
                bottom_navigation_view_activity_home.show()
            }
        }
    }
}
