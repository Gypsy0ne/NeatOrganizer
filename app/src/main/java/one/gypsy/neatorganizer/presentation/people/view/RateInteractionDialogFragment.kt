package one.gypsy.neatorganizer.presentation.people.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentRateInteractionBinding
import one.gypsy.neatorganizer.presentation.people.vm.RateInteractionViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RateInteractionDialogFragment : BottomSheetDialogFragment() {

    private val args: RateInteractionDialogFragmentArgs by navArgs()
    private val viewModel: RateInteractionViewModel by viewModel {
        parametersOf(args.personId)
    }
    private lateinit var fragmentBinding: DialogFragmentRateInteractionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_rate_interaction,
            container,
            false
        )
        return fragmentBinding.root
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