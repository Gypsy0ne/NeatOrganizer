package one.gypsy.neatorganizer.note.view.widget.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import one.gypsy.neatorganizer.core.utils.extensions.showShortToast
import one.gypsy.neatorganizer.core.widget.WidgetConfigurationActivity
import one.gypsy.neatorganizer.core.widget.WidgetKeyring
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.FragmentWidgetNoteSelectionBinding
import one.gypsy.neatorganizer.note.vm.NoteWidgetConfigurationViewModel
import one.gypsy.neatorganizer.note.vm.NoteWidgetCreationStatus
import org.koin.android.viewmodel.ext.android.viewModel

class WidgetNoteSelectionFragment : Fragment() {

    private val viewModel: NoteWidgetConfigurationViewModel by viewModel()
    private lateinit var viewBinding: FragmentWidgetNoteSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_widget_note_selection,
            container,
            false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        viewBinding.bindViews()
        observeCreationStatus()
    }

    private fun FragmentWidgetNoteSelectionBinding.bindViews() {
        configurationViewModel = viewModel
        lifecycleOwner = viewLifecycleOwner
        bindButtons()
        bindRecyclerView()
        executePendingBindings()
    }

    private fun FragmentWidgetNoteSelectionBinding.bindButtons() {
        cancelConfiguration.setOnClickListener {
            requireActivity().finish()
        }
        submitConfiguration.setOnClickListener {
            viewModel.saveNoteWidget(retrieveWidgetId())
        }
    }

    private fun retrieveWidgetId() =
        arguments?.getInt(NoteWidgetConfigureActivity.CONFIGURED_WIDGET_ID)
            ?: WidgetKeyring.MANAGED_WIDGET_INVALID_ID

    private fun FragmentWidgetNoteSelectionBinding.bindRecyclerView() {
        notesAdapter = WidgetNoteEntriesAdapter(
            viewModel.selectedNote,
            { viewModel.onNoteSelected(it) },
            { findNavController().navigate(R.id.noteAddition) }
        )
        layoutManager = GridLayoutManager(requireContext(), COLUMNS_COUNT).apply {
            spanSizeLookup = createGridSpanLookupStrategy()
        }
    }

    private fun createGridSpanLookupStrategy() = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int =
            if (position == viewModel.listedNotes.value?.lastIndex) {
                UTIL_GRID_SPAN_COUNT
            } else {
                ENTRY_GRID_SPAN_COUNT
            }
    }

    private fun observeCreationStatus() {
        viewModel.widgetCreationStatus.observe(viewLifecycleOwner) {
            when (it) {
                NoteWidgetCreationStatus.NoteNotSelectedStatus -> {
                    requireContext().showShortToast(resources.getString(R.string.note_widget_creation_task_warning))
                }
                NoteWidgetCreationStatus.CreationSuccessStatus -> {
                    (requireActivity() as? WidgetConfigurationActivity)?.onWidgetCreationFinish()
                }
            }
        }
    }

    companion object {
        private const val COLUMNS_COUNT = 2
        private const val ENTRY_GRID_SPAN_COUNT = 1
        private const val UTIL_GRID_SPAN_COUNT = 2
    }
}
