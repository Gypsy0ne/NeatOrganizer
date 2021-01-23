package one.gypsy.neatorganizer.presentation.notes.view.widget.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentNoteDetailsBinding
import one.gypsy.neatorganizer.presentation.notes.vm.NoteViewModel
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.MANAGED_NOTE_ID_KEY
import one.gypsy.neatorganizer.presentation.tasks.view.widget.NoteWidgetKeyring.MANAGED_NOTE_INVALID_ID
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteManageFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModel {
        parametersOf(arguments?.getLong(MANAGED_NOTE_ID_KEY) ?: MANAGED_NOTE_INVALID_ID)
    }
    private lateinit var viewBinding: FragmentNoteDetailsBinding
//    private val subItemClickListener = TaskSubItemClickListener(
//        onDoneClick = { tasksViewModel.onTaskUpdate(it) },
//        onEditionSubmitClick = { tasksViewModel.onTaskUpdate(it) },
//        onRemoveClick = { tasksViewModel.onRemove(it) }
//    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_note_details,
            container,
            false
        )
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setUpContentBinding()
//        findNavController().observeNewGroupSelectionResult()
    }

    private fun FragmentNoteDetailsBinding.setUpContentBinding() {
        viewModel = noteViewModel
        lifecycleOwner = this@NoteManageFragment
        executePendingBindings()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.add_entry -> {
//            findNavController(this).navigateToAddTaskDialog()
//            true
//        }
//        else -> false
//    }

//    private fun NavController.observeNewGroupSelectionResult() =
//        currentBackStackEntry
//            ?.savedStateHandle
//            ?.getLiveData<Long?>(SELECTED_WIDGET_GROUP_ID_KEY)
//            ?.observe(viewLifecycleOwner) {
//                onNewTaskGroupSelected(it)
//            }
//
//    private fun onNewTaskGroupSelected(selectedGroupId: Long?) = selectedGroupId?.let {
//        noteViewModel.loadTasksData(it)
//    }
}
