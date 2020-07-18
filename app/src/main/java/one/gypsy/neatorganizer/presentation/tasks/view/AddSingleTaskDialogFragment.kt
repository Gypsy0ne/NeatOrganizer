package one.gypsy.neatorganizer.presentation.tasks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentAddTaskBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.AddTaskViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddSingleTaskDialogFragment : BottomSheetDialogFragment() {

    private val args: AddSingleTaskDialogFragmentArgs by navArgs()
    private val viewModel: AddTaskViewModel by viewModel {
        parametersOf(args.groupId)
    }
    lateinit var fragmentBinding: DialogFragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_add_task, container, false)
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
        viewModel.finishedAdding.observe(viewLifecycleOwner, Observer { finished ->
            if (finished)
                findNavController().popBackStack()
        })
    }

}