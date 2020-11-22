package one.gypsy.neatorganizer.presentation.routines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentRoutinesBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class RoutinesFragment : SectionFragment() {

    private val routinesViewModel: RoutinesViewModel by viewModel()
    private lateinit var fragmentBinding: FragmentRoutinesBinding

    private val headerClickListener = RoutineHeaderClickListener(
        onEditionSubmitClick = { routinesViewModel.onHeaderUpdate(it) },
        onExpanderClick = { routinesViewModel.onExpand(it) },
        onRemoveClick = { navigateToRemoveRoutineSubmitDialog(it.id, it.subItemsCount) }
    )

    private val subItemClickListener = RoutineSubItemClickListener(
        onRemoveClick = { routinesViewModel.onRemove(it) },
        onEditionSubmitClick = { routinesViewModel.onTaskUpdate(it) },
        onDoneClick = { routinesViewModel.onTaskUpdate(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_routines, container, false)
        return fragmentBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.routine_add)
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLayoutBinding()
        setUpRecyclerView()
    }

    private fun setUpLayoutBinding() {
        fragmentBinding.apply {
            viewModel = routinesViewModel
            lifecycleOwner = this@RoutinesFragment
        }
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        linearLayoutManager = LinearLayoutManager(context)
        routinesAdapter = RoutinesAdapter(headerClickListener, subItemClickListener)
        recyclerViewFragmentTasks.itemAnimator = null
        executePendingBindings()
    }

    private fun navigateToRemoveRoutineSubmitDialog(routineId: Long, subItemsCount: Int) {
        with(
            RoutinesFragmentDirections.actionRoutinesToRemoveRoutineSubmitDialogFragment(
                routineId,
                subItemsCount
            )
        ) {
            findNavController().navigate(this)
        }
    }
}