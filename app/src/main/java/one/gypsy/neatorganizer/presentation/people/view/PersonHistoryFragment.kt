package one.gypsy.neatorganizer.presentation.people.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentPersonHistoryBinding
import one.gypsy.neatorganizer.injector

class PersonHistoryFragment: Fragment() {

    private val args: PersonHistoryFragmentArgs by navArgs()

    private val personHistoryViewModel by lazy {
        injector.personHistoryViewModelFactory.create(
            args.personId
        )
    }

    private lateinit var fragmentBinding: FragmentPersonHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_person_history, container, false)
        return fragmentBinding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = personHistoryViewModel
        fragmentBinding.lifecycleOwner = this
    }

}