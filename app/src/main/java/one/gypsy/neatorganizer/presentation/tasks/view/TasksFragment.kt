package one.gypsy.neatorganizer.presentation.tasks.view

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
import one.gypsy.neatorganizer.databinding.FragmentTasksBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel
import javax.inject.Inject

class TasksFragment : SectionFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tasksViewModel: TasksViewModel

    private lateinit var fragmentBinding: FragmentTasksBinding

    val headerClickListener by lazy {
        object: TaskHeaderViewHolder.ClickListener {
            override fun onExpanderClick(headerItem: TaskListItem.TaskListHeader) {
                tasksViewModel.onExpanderClicked(headerItem)
            }

            override fun onEditionSubmitClick(headerItem: TaskListItem.TaskListHeader) {
                tasksViewModel.onEditionSubmit(headerItem)
            }
        }
    }

    val subItemClickListener by lazy {
        object: TaskSubItemViewHolder.ClickListener {
            override fun onDoneClick(subItem: TaskListItem.TaskListSubItem) {
                tasksViewModel
            }

            override fun onEditionSubmitClick(subItem: TaskListItem.TaskListSubItem) {
                tasksViewModel.onEditionSubmit(subItem)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false)
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasksViewModel = ViewModelProviders.of(this, viewModelFactory)[TasksViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.viewModel = tasksViewModel
        fragmentBinding.lifecycleOwner = this
        setUpRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.task_group_add)
        return true
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = GroupedTasksAdapter(headerClickListener, subItemClickListener)
        executePendingBindings()
    }
}