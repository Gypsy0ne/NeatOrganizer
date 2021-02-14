package one.gypsy.neatorganizer.routine.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.routine.R
import one.gypsy.neatorganizer.routine.databinding.DialogFragmentAddRoutineTaskBinding
import one.gypsy.neatorganizer.routine.vm.AddRoutineTaskViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddRoutineTaskDialogFragment : BottomSheetDialogFragment() {

    private val args: AddRoutineTaskDialogFragmentArgs by navArgs()
    private val viewModel: AddRoutineTaskViewModel by viewModel {
        parametersOf(args.routineId)
    }
    private lateinit var fragmentBinding: DialogFragmentAddRoutineTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Transparent)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_fragment_add_routine_task,
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
        viewModel.finishedAdding.observe(
            viewLifecycleOwner,
            Observer { finished ->
                if (finished)
                    findNavController().popBackStack()
            }
        )
    }
}
