package one.gypsy.neatorganizer.presentation.routines.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentRoutinesBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.listing.HeaderClickListener
import one.gypsy.neatorganizer.presentation.listing.SubItemClickListener
import one.gypsy.neatorganizer.presentation.routines.model.RoutineListItem
import one.gypsy.neatorganizer.presentation.routines.vm.RoutinesViewModel
import javax.inject.Inject

class RoutinesFragment : SectionFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var routinesViewModel: RoutinesViewModel
    private lateinit var fragmentBinding: FragmentRoutinesBinding

    val headerClickListener by lazy {
        object : HeaderClickListener<RoutineListItem.RoutineListHeader> {
            override fun onExpanderClick(headerItem: RoutineListItem.RoutineListHeader) {
                routinesViewModel.onExpand(headerItem)
                RemoveRoutineSubmitDialogFragment().showNow(parentFragmentManager, "")
            }

            override fun onEditionSubmitClick(headerItem: RoutineListItem.RoutineListHeader) {
                routinesViewModel.onHeaderUpdate(headerItem)
            }

            override fun onRemoveClick(headerItem: RoutineListItem.RoutineListHeader) {
                routinesViewModel.onRemove(headerItem)
            }
        }
    }

    val subItemClickListener by lazy {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        routinesViewModel =
            ViewModelProviders.of(this, viewModelFactory)[RoutinesViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
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
}