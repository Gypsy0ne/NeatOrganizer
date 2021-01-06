package one.gypsy.neatorganizer.presentation.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentSingleRemoveConfirmationBinding
import one.gypsy.neatorganizer.presentation.notes.vm.DeleteNoteViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DeleteNoteDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: DeleteNoteViewModel by viewModel()
    private lateinit var fragmentBinding: DialogFragmentSingleRemoveConfirmationBinding
    private val args: DeleteNoteDialogFragmentArgs by navArgs()

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
            R.layout.dialog_fragment_single_remove_confirmation,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.lifecycleOwner = this
        fragmentBinding.setSubmitClickListener {
            viewModel.onRemoveSubmit(args.noteId)
        }
    }

    override fun onStart() {
        super.onStart()
        setUpObservers()
    }

    private fun setUpObservers() =
        viewModel.actionFinished.observe(viewLifecycleOwner) { finished ->
            if (finished)
                findNavController().popBackStack()
        }
}
