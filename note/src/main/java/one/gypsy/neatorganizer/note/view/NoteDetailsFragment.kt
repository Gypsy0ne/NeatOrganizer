package one.gypsy.neatorganizer.note.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.autofit.et.lib.AutoFitEditText
import one.gypsy.neatorganizer.core.binding.setEditionEnabled
import one.gypsy.neatorganizer.note.R
import one.gypsy.neatorganizer.note.databinding.FragmentNoteDetailsBinding
import one.gypsy.neatorganizer.note.vm.NoteViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class NoteDetailsFragment :
    one.gypsy.neatorganizer.core.SectionFragment(R.menu.note_details_menu) {

    private val args: NoteDetailsFragmentArgs by navArgs()
    private val detailsViewModel: NoteViewModel by viewModel {
        parametersOf(args.noteId)
    }
    private lateinit var fragmentBinding: FragmentNoteDetailsBinding
    private val titleView by lazy {
        (activity as? AppCompatActivity)?.supportActionBar?.customView?.findViewById<AutoFitEditText>(
            R.id.barTitle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentNoteDetailsBinding>(
        inflater,
        R.layout.fragment_note_details,
        container,
        false
    ).also {
        fragmentBinding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.apply {
            viewModel = detailsViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setTitleBarCustomView()
        initTitleViewBehavior()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearTitleBarCustomView()
    }

    private fun setTitleBarCustomView() =
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setCustomView(R.layout.editable_title_bar)
        }

    private fun initTitleViewBehavior() =
        titleView?.also { titleView ->
            detailsViewModel.edited.observe(viewLifecycleOwner) {
                setEditionEnabled(titleView, it, false)
            }
            detailsViewModel.note.observe(viewLifecycleOwner) {
                titleView.setText(it.title)
            }
        }

    private fun clearTitleBarCustomView() =
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayShowCustomEnabled(false)
            customView = null
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
        putExtra(Intent.EXTRA_TEXT, detailsViewModel.note.value?.content.orEmpty())
        type = SHARE_CONTENT_TYPE
    }.let {
        startActivity(Intent.createChooser(it, null))
    }

    private fun onEditNoteClicked() {
        appBarMenu.findItem(R.id.edit_note).isVisible = false
        appBarMenu.findItem(R.id.save_note).isVisible = true
        detailsViewModel.onEditIconClicked()
    }

    private fun onSaveNoteClicked() {
        appBarMenu.findItem(R.id.edit_note).isVisible = true
        appBarMenu.findItem(R.id.save_note).isVisible = false
        titleView?.let {
            detailsViewModel.onEditionFinish(
                it.text.toString(),
                fragmentBinding.noteContent.text.toString()
            )
        }
        detailsViewModel.onEditIconClicked()
    }

    private companion object {
        const val SHARE_CONTENT_TYPE = "text/plain"
    }
}
