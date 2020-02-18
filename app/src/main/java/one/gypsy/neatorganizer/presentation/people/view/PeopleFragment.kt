package one.gypsy.neatorganizer.presentation.people.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentPeopleBinding
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import javax.inject.Inject


//TODO https://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
//https://wrdlbrnft.github.io/SortedListAdapter/
class PeopleFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var peopleViewModel: PeopleViewModel


    private lateinit var fragmentBinding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        peopleViewModel = ViewModelProviders.of(this, viewModelFactory)[PeopleViewModel::class.java]
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = peopleViewModel
        fragmentBinding.lifecycleOwner = this
        setUpRecyclerView()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        setUpSearchViewIcons(menu)
        super.onPrepareOptionsMenu(menu)
    }

    private fun setUpSearchViewIcons(menu: Menu) {
        val searchViewMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchViewMenuItem.actionView as SearchView
        val searchImgId = R.id.search_button
        val closeImgId = R.id.search_close_btn
        val searchImage = searchView.findViewById(searchImgId) as ImageView
        val closeImage = searchView.findViewById(closeImgId) as ImageView
        searchImage.setImageResource(R.drawable.ic_search_white_24dp)
        closeImage.setImageResource(R.drawable.ic_close_white_24dp)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_home_app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        val peopleAdapter = PeopleAdapter()
        fragmentBinding.peopleAdapter = peopleAdapter
        layoutManager = LinearLayoutManager(context)
        executePendingBindings()
    }
}