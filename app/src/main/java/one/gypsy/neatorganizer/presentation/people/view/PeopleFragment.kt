package one.gypsy.neatorganizer.presentation.people.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentPeopleBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import javax.inject.Inject


//TODO https://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
//https://wrdlbrnft.github.io/SortedListAdapter/
class PeopleFragment : SectionFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var peopleViewModel: PeopleViewModel


    private lateinit var fragmentBinding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.person_add)
        return true
    }


    private fun setUpRecyclerView() = fragmentBinding.apply {
        val peopleAdapter = PeopleAdapter()
        fragmentBinding.peopleAdapter = peopleAdapter
        layoutManager = LinearLayoutManager(context)
        executePendingBindings()
    }
}