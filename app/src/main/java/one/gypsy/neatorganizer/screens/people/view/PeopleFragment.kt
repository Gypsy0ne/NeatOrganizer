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

class PeopleFragment : Fragment() {

    private val peopleViewModel: PeopleViewModel by lazy {
        ViewModelProviders.of(this)[PeopleViewModel::class.java]
    }

    private lateinit var fragmentBinding: FragmentPeopleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        return fragmentBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = peopleViewModel
        fragmentBinding.lifecycleOwner = this
    }
}