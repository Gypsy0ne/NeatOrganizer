package one.gypsy.neatorganizer.presentation.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentAddNoteBinding
import one.gypsy.neatorganizer.presentation.notes.vm.AddNoteViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DeleteNoteDialogFragment : BottomSheetDialogFragment() {

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
        setUpObservers()
    }

    private fun setUpObservers() {
//        viewModel.finishedAdding.observe(viewLifecycleOwner) { finished ->
//            if (finished)
//                findNavController().popBackStack()
//        }
    }
}
