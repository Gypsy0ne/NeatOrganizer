package one.gypsy.neatorganizer.note.view.widget.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_select_note.*
import one.gypsy.neatorganizer.core.utils.extensions.showShortToast
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.DialogFragmentSelectNoteBinding
import one.gypsy.neatorganizer.note.model.WidgetNoteItem
import one.gypsy.neatorganizer.note.view.widget.NoteWidgetKeyring.SELECTED_WIDGET_NOTE_ID_KEY
import one.gypsy.neatorganizer.note.view.widget.configuration.WidgetNoteEntriesAdapter
import one.gypsy.neatorganizer.note.vm.NoteWidgetSelectionStatus
import one.gypsy.neatorganizer.note.vm.NoteWidgetSelectionViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class NoteSelectionDialogFragment : BottomSheetDialogFragment() {

    private val args: NoteSelectionDialogFragmentArgs by navArgs()
    private val selectionViewModel: NoteWidgetSelectionViewModel by viewModel {
        parametersOf(args.widgetId)
    }
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
        observeSelectionStatus()
    }

    private fun observeSelectionStatus() = selectionViewModel
        .widgetSelectionStatus
        .observe(this@NoteSelectionDialogFragment) {
            when (it) {
                NoteWidgetSelectionStatus.NoteNotSelectedStatus -> requireContext().showShortToast(
                    resources.getString(R.string.note_widget_creation_task_warning)
                )
                NoteWidgetSelectionStatus.SelectionSuccessStatus -> finishSelection(
                    (selectionViewModel.selectedNote.value as? WidgetNoteItem.EntryItem)?.id
                )
            }
        }

    private fun DialogFragmentSelectNoteBinding.bindLayout() {
        viewModel = selectionViewModel
        lifecycleOwner = this@NoteSelectionDialogFragment
        bindViews()
    }

    private fun DialogFragmentSelectNoteBinding.bindViews() {
        bindRecyclerView()
        bindButtons()
    }

    private fun DialogFragmentSelectNoteBinding.bindRecyclerView() {
        notesAdapter = WidgetNoteEntriesAdapter(
            selectionViewModel.selectedNote,
            { selectionViewModel.onItemSelected(it) }
        )
        layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
    }

    private fun DialogFragmentSelectNoteBinding.bindButtons() {
        selectionCancelation.setOnClickListener { cancelSelection(null) }
        selectionConfirmation.setOnClickListener { selectionViewModel.onSubmitClicked() }
    }

    private fun cancelSelection(selectedNoteId: Long?) {
        setSelectionResult(selectedNoteId)
        requireActivity().finish()
    }

    private fun finishSelection(selectedNoteId: Long?) {
        setSelectionResult(selectedNoteId)
        dismiss()
    }

    private fun setSelectionResult(selectedNoteId: Long?) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            SELECTED_WIDGET_NOTE_ID_KEY,
            selectedNoteId
        )
    }

    private companion object {
        const val GRID_SPAN_COUNT = 2
    }
}
