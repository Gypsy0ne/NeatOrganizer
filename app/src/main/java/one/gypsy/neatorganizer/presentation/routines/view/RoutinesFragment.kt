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
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class RoutinesFragment : SectionFragment() {

    private val routinesViewModel: RoutinesViewModel by viewModel()
    private lateinit var fragmentBinding: FragmentRoutinesBinding

    private val headerClickListener by lazy {
        object : HeaderClickListener<RoutineListItem.RoutineListHeader> {
            override fun onExpanderClick(headerItem: RoutineListItem.RoutineListHeader) {
                routinesViewModel.onExpand(headerItem)
            }

            override fun onEditionSubmitClick(headerItem: RoutineListItem.RoutineListHeader) {
                routinesViewModel.onHeaderUpdate(headerItem)
            }

            override fun onRemoveClick(headerItem: RoutineListItem.RoutineListHeader) {
                navigateToRemoveRoutineSubmitDialog(headerItem.id, headerItem.subItemsCount)
            }
        }
    }

    private val subItemClickListener by lazy {
        object : SubItemClickListener<RoutineListItem.RoutineListSubItem> {
            override fun onDoneClick(subItem: RoutineListItem.RoutineListSubItem) {
                routinesViewModel.onTaskUpdate(subItem)
            }

            override fun onEditionSubmitClick(subItem: RoutineListItem.RoutineListSubItem) {
                routinesViewModel.onTaskUpdate(subItem)
            }

            override fun onRemoveClick(subItem: RoutineListItem.RoutineListSubItem) {
                routinesViewModel.onRemove(subItem)
            }

        }
    }

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