package one.gypsy.neatorganizer.routine.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.core.databinding.DialogFragmentGroupRemoveConfirmationBinding
import one.gypsy.neatorganizer.routine.R
import one.gypsy.neatorganizer.routine.vm.RemoveRoutineViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RemoveRoutineSubmitDialogFragment : BottomSheetDialogFragment() {

    private lateinit var fragmentBinding: DialogFragmentGroupRemoveConfirmationBinding
    private val args: RemoveRoutineSubmitDialogFragmentArgs by navArgs()
    private val viewModel: RemoveRoutineViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_group_remove_confirmation,
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
        viewModel.actionFinished.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        fragmentBinding.subItemsCount = args.subItemsCount
        fragmentBinding.removeGroupType = resources.getString(R.string.remove_routine_subject)
    }
}
