package one.gypsy.neatorganizer.presentation.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentNotesBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.notes.vm.NotesListingViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NotesFragment : SectionFragment() {

    private val notesViewModel: NotesListingViewModel by viewModel()
    private lateinit var fragmentBinding: FragmentNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notes,
            container,
            false
        )
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.apply {
            viewModel = notesViewModel
            lifecycleOwner = this@NotesFragment
        }
        setUpRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.noteAddition)
        return true
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        noteEntriesAdapter = NoteEntriesAdapter()
        notesListing.setOnClickListener { }
        notesListing.itemAnimator = null
        executePendingBindings()
    }

    private companion object {
        const val GRID_SPAN_COUNT = 2
    }
}
