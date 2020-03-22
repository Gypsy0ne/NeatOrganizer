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
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_tasks.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.FragmentTasksBinding
import one.gypsy.neatorganizer.presentation.SectionFragment
import one.gypsy.neatorganizer.presentation.tasks.vm.TasksViewModel
import one.gypsy.neatorganizer.utils.extensions.collapseSection
import one.gypsy.neatorganizer.utils.wrappers.CollapseListener
import javax.inject.Inject

class TasksFragment : SectionFragment() {

    val collapseListener = object : CollapseListener {
        override fun onCollapse(section: Section) {
            (recycler_view_fragment_tasks.adapter as SectionedRecyclerViewAdapter).also {
                it.collapseSection(section)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tasksViewModel: TasksViewModel

    private lateinit var fragmentBinding: FragmentTasksBinding


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

    override fun onResume() {
        super.onResume()
    }

    private fun setUpRecyclerView() = fragmentBinding.apply {
        linearLayoutManager = LinearLayoutManager(context)
        tasksAdapter = SectionedRecyclerViewAdapter()
        this.collapseListener =  this@TasksFragment.collapseListener
        executePendingBindings()
    }

//    private fun createTasksAdapter() = SectionedRecyclerViewAdapter().apply {
//        //TODO
//    }


}