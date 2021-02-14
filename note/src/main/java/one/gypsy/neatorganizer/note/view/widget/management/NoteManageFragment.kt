package one.gypsy.neatorganizer.note.view.widget.management

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.autofit.et.lib.AutoFitEditText
import one.gypsy.neatorganizer.core.binding.setEditionEnabled
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_ID_KEY
import one.gypsy.neatorganizer.core.widget.WidgetKeyring.MANAGED_WIDGET_INVALID_ID
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.FragmentNoteManageBinding
import one.gypsy.neatorganizer.note.view.widget.NoteWidgetKeyring.MANAGED_NOTE_ID_KEY
import one.gypsy.neatorganizer.note.view.widget.NoteWidgetKeyring.MANAGED_NOTE_INVALID_ID
import one.gypsy.neatorganizer.note.view.widget.NoteWidgetKeyring.SELECTED_WIDGET_NOTE_ID_KEY
import one.gypsy.neatorganizer.note.vm.NoteManageLoadingStatus
import one.gypsy.neatorganizer.note.vm.NoteWidgetContentManageViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteManageFragment : Fragment() {

    private val noteViewModel: NoteWidgetContentManageViewModel by viewModel {
        parametersOf(arguments?.getLong(MANAGED_NOTE_ID_KEY) ?: MANAGED_NOTE_INVALID_ID)
    }
    private lateinit var viewBinding: FragmentNoteManageBinding
    private lateinit var appBarMenu: Menu
    private val titleView by lazy {
        (activity as? AppCompatActivity)?.supportActionBar
            ?.customView
            ?.findViewById<AutoFitEditText>(R.id.barTitle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_note_manage,
            container,
            false
        )
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setUpContentBinding()
        findNavController().observeNewNoteSelectionResult()
        setTitleBarCustomView()
        initTitleViewBehavior()
        observeDataLoadingStatus()
    }

    private fun FragmentNoteManageBinding.setUpContentBinding() {
        viewModel = noteViewModel
        lifecycleOwner = this@NoteManageFragment
        executePendingBindings()
    }

    private fun setTitleBarCustomView() =
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.editable_title_bar)
        }

    private fun initTitleViewBehavior() =
        titleView?.also { titleView ->
            noteViewModel.edited.observe(viewLifecycleOwner) { edited ->
                setEditionEnabled(titleView, edited, false)
            }
            noteViewModel.note.observe(viewLifecycleOwner) { note ->
                titleView.setText(note.title)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        clearTitleBarCustomView()
    }

    private fun clearTitleBarCustomView() =
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayShowCustomEnabled(false)
            customView = null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_details_menu, menu)
        appBarMenu = menu
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_note -> shareNoteContent()
            R.id.edit_note -> onEditNoteClicked()
            R.id.save_note -> onSaveNoteClicked()
        }
        return true
    }

    private fun shareNoteContent() = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, noteViewModel.note.value?.content.orEmpty())
        type = SHARE_CONTENT_TYPE
    }.let {
        startActivity(Intent.createChooser(it, null))
    }

    private fun onEditNoteClicked() {
        appBarMenu.findItem(R.id.edit_note).isVisible = false
        appBarMenu.findItem(R.id.save_note).isVisible = true
        noteViewModel.onEditIconClicked()
    }

    private fun onSaveNoteClicked() {
        appBarMenu.findItem(R.id.edit_note).isVisible = true
        appBarMenu.findItem(R.id.save_note).isVisible = false
        titleView?.let {
            noteViewModel.onEditionFinish(
                it.text.toString(),
                viewBinding.noteContent.text.toString()
            )
        }
        noteViewModel.onEditIconClicked()
    }

    private fun NavController.observeNewNoteSelectionResult() =
        currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Long?>(SELECTED_WIDGET_NOTE_ID_KEY)
            ?.observe(viewLifecycleOwner) {
                onNewNoteSelected(it)
            }

    private fun onNewNoteSelected(selectedNoteId: Long?) = selectedNoteId?.let {
        noteViewModel.loadNoteData(it)
    }

    private fun observeDataLoadingStatus() =
        noteViewModel.dataLoadingStatus.observe(viewLifecycleOwner) {
            if (it == NoteManageLoadingStatus.Error) {
                findNavController().navigateToSelectNoteDialog()
            }
        }

    private fun NavController.navigateToSelectNoteDialog() {
        val widgetId = arguments?.getInt(MANAGED_WIDGET_ID_KEY) ?: MANAGED_WIDGET_INVALID_ID
        if (widgetId != MANAGED_WIDGET_INVALID_ID) {
            navigate(
                NoteManageFragmentDirections
                    .widgetNoteManageToNoteSelection(widgetId)
            )
        }
    }

    companion object {
        private const val SHARE_CONTENT_TYPE = "text/plain"
    }
}
