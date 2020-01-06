package one.gypsy.neatorganizer.screens.people.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentPeopleBinding
import one.gypsy.neatorganizer.screens.people.vm.PeopleViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_people.*
import javax.inject.Inject

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
        fragmentBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
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

    private fun setUpRecyclerView() {
        recycler_view_fragment_people.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = PeopleAdapter()
        }
    }
}