package one.gypsy.neatorganizer.presentation.routines.view

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
import one.gypsy.neatorganizer.databinding.DialogFragmentRemoveConfirmationBinding
import one.gypsy.neatorganizer.presentation.routines.vm.RemoveRoutineViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class RemoveRoutineSubmitDialogFragment : BottomSheetDialogFragment() {

    private lateinit var fragmentBinding: DialogFragmentRemoveConfirmationBinding
    private val args: RemoveRoutineSubmitDialogFragmentArgs by navArgs()
    private val viewModel: RemoveRoutineViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_remove_confirmation,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Transparent)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.setSubmitClickListener {
            viewModel.onRemoveSubmit(args.itemId)
        }
        viewModel.actionFinished.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().popBackStack()
            }
        })
        fragmentBinding.subItemsCount = args.subItemsCount
    }
}