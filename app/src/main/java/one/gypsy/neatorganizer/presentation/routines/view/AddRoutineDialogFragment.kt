package one.gypsy.neatorganizer.presentation.routines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentAddRoutineBinding
import one.gypsy.neatorganizer.presentation.routines.vm.AddRoutineViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddRoutineDialogFragment : BottomSheetDialogFragment() {

    private val addRoutineViewModel: AddRoutineViewModel by viewModel()
    private lateinit var fragmentBinding: DialogFragmentAddRoutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Transparent)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_add_routine,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.apply {
            this.viewModel = addRoutineViewModel
            lifecycleOwner = this@AddRoutineDialogFragment
        }
    }

    override fun onStart() {
        super.onStart()
        setUpObservers()
    }

    private fun setUpObservers() {
        addRoutineViewModel.finishedAdding.observe(viewLifecycleOwner, Observer { finished ->
            if (finished)
                findNavController().popBackStack()
        })
    }
}