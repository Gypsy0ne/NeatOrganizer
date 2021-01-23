package one.gypsy.neatorganizer.presentation.notes.view.widget.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_select_note.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.DialogFragmentSelectNoteBinding
import one.gypsy.neatorganizer.presentation.notes.vm.NoteWidgetSelectionViewModel
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.SELECTED_WIDGET_NOTE_ID_KEY
import org.koin.android.viewmodel.ext.android.viewModel

class NoteSelectionDialogFragment : BottomSheetDialogFragment() {

    private val args: NoteSelectionDialogFragmentArgs by navArgs()
    private val selectionViewModel: NoteWidgetSelectionViewModel by viewModel()
    lateinit var fragmentBinding: DialogFragmentSelectNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Transparent)
        isCancelable = false
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_fragment_select_note,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.bindLayout()
    }

    //        private fun observeSelectionStatus() = selectionViewModel
//        .widgetSelectionStatus
//        .observe(this@NoteSelectionDialogFragment) {
//            when (it) {
//                TaskWidgetSelectionStatus.TaskGroupNotSelectedStatus -> context?.showShortToast(
//                    resources.getString(R.string.task_widget_creation_task_warning)
//                )
//                TaskWidgetSelectionStatus.SelectionSuccessStatus -> dismissWithSelectionResult(
//                    selectionViewModel.selectedTaskGroup.value?.id
//                )
//            }
//        }
//
    private fun DialogFragmentSelectNoteBinding.bindLayout() {
        viewModel = selectionViewModel
        lifecycleOwner = this@NoteSelectionDialogFragment
        bindViews()
    }

    //
    private fun DialogFragmentSelectNoteBinding.bindViews() {
//        bindRecyclerView()
        bindButtons()
    }

    //
//    private fun NoteWidgetSelectionViewModel.bindRecyclerView() {
//        tasksAdapter = TaskGroupEntriesAdapter(selectionViewModel.selectedTaskGroup) {
//            selectionViewModel.onTaskGroupSelected(it)
//        }
//        layoutManager = LinearLayoutManager(context)
//    }
//
    private fun DialogFragmentSelectNoteBinding.bindButtons() {
        selectionCancelation.setOnClickListener { dismissWithSelectionResult(null) }
        selectionConfirmation.setOnClickListener { selectionViewModel.onSubmitClicked(args.widgetId) }
    }

    private fun dismissWithSelectionResult(selectedNoteId: Long?) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            SELECTED_WIDGET_NOTE_ID_KEY,
            selectedNoteId
        )
        requireActivity().finish()
    }
}
