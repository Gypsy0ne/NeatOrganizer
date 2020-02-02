package one.gypsy.neatorganizer.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import one.gypsy.neatorganizer.R

abstract class SectionFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_home_app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

}