package one.gypsy.neatorganizer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.utils.extensions.hide
import one.gypsy.neatorganizer.utils.extensions.show

class HomeActivity : AppCompatActivity() {

    private val navController by lazy {
        findNavController(R.id.fragment_activity_home_nav_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpActionBar()
        setUpNavigationListener()
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setUpNavigationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.person_profile_fragment -> bottom_navigation_view_activity_home.hide()
                else -> bottom_navigation_view_activity_home.show()
            }
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val navigation = findNavController(R.id.fragment_activity_home_nav_container)
//        return item.onNavDestinationSelected(navigation) || super.onOptionsItemSelected(item)
//    }

    override fun onStart() {
        super.onStart()
        setUpBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_activity_home_nav_container).navigateUp()
    }


    private fun setUpBottomNavigation() {
        bottom_navigation_view_activity_home.setupWithNavController(findNavController(R.id.fragment_activity_home_nav_container))
    }

}
