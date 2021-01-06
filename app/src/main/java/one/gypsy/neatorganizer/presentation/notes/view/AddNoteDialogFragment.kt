package one.gypsy.neatorganizer.presentation.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentAddNoteBinding
import one.gypsy.neatorganizer.presentation.notes.vm.AddNoteViewModel
import one.gypsy.neatorganizer.presentation.notes.vm.NoteCreationStatus
import one.gypsy.neatorganizer.utils.extensions.showShortToast
import org.koin.android.viewmodel.ext.android.viewModel

class AddNoteDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: AddNoteViewModel by viewModel()
    private lateinit var fragmentBinding: DialogFragmentAddNoteBinding

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
            R.layout.dialog_fragment_add_note,
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
        observeCreationStatus()
    }

    private fun observeCreationStatus() {
        viewModel.noteCreationStatus.observe(this) {
            if (it == NoteCreationStatus.ColorNotPickedStatus) {
                context?.showShortToast((resources.getString(R.string.task_widget_creation_color_warning)))
            } else {
                findNavController().popBackStack()
            }
        }
    }
}
