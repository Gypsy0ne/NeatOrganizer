package one.gypsy.neatorganizer.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_home.*
import one.gypsy.neatorganizer.R
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasAndroidInjector {

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar_activity_home)
        AndroidInjection.inject(this)
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
