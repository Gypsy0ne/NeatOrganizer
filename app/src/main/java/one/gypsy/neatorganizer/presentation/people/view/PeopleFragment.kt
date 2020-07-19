package one.gypsy.neatorganizer.presentation.people.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentPeopleBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.people.vm.PeopleViewModel
import org.koin.android.viewmodel.ext.android.viewModel

//TODO https://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
//https://wrdlbrnft.github.io/SortedListAdapter/
class PeopleFragment : SectionFragment() {

    //    interface PeopleInteractionListener {
//        fun onPersonEntryUpdateTriggered(personId: Long)
//    }
    private val peopleViewModel: PeopleViewModel by viewModel()
    private lateinit var fragmentBinding: FragmentPeopleBinding

//    private val peopleInteractionListener = object : PeopleInteractionListener {
//        override fun onPersonEntryUpdateTriggered(personId: Long) {
////            peopleViewModel.updatePersonInteraction(personId)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        return fragmentBinding.root
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
        peopleAdapter = PeopleAdapter()
        layoutManager = LinearLayoutManager(context)
        executePendingBindings()
    }

}