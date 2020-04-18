package one.gypsy.neatorganizer.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import one.gypsy.neatorganizer.R

abstract class SectionFragment: Fragment() {

//    protected fun setUpSearchViewIcons(menu: Menu) {
//        val searchViewMenuItem = menu.findItem(R.id.action_search)
//        val searchView = searchViewMenuItem.actionView as SearchView
//        val searchImgId = R.id.search_button
//        val closeImgId = R.id.search_close_btn
//        val searchImage = searchView.findViewById(searchImgId) as ImageView
//        val closeImage = searchView.findViewById(closeImgId) as ImageView
//        searchImage.setImageResource(R.drawable.ic_search_white_24dp)
//        closeImage.setImageResource(R.drawable.ic_close_white_24dp)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
//        setUpSearchViewIcons(menu)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_home_app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

}