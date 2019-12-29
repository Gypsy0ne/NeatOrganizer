package one.gypsy.neatorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

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
