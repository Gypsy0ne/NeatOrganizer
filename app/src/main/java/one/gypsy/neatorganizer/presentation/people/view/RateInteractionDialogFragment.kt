package one.gypsy.neatorganizer.presentation.people.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentRateInteractionBinding
import one.gypsy.neatorganizer.presentation.injector
import one.gypsy.neatorganizer.presentation.people.vm.RateInteractionViewModel
import one.gypsy.neatorganizer.presentation.profile.PersonProfileFragmentArgs
import javax.inject.Inject

class RateInteractionDialogFragment: BottomSheetDialogFragment() {
    private val args: RateInteractionDialogFragmentArgs by navArgs()

    val viewModel by lazy {
        injector.rateInteractionViewModelFactory.create(
            args.personId
        )
    }

    lateinit var fragmentBinding: DialogFragmentRateInteractionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_rate_interaction, container, false)
        return  fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = viewModel
        fragmentBinding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.interactionUpdateStatus.observe(viewLifecycleOwner, Observer {
            dismiss()
        })
    }
}