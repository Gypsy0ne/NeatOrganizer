package one.gypsy.neatorganizer.presentation.tasks.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_task_group_manage.*
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.databinding.ActivityTaskGroupManageBinding
import one.gypsy.neatorganizer.presentation.tasks.vm.TaskWidgetManageViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TaskGroupManageActivity : AppCompatActivity() {
    private val tasksViewModel: TaskWidgetManageViewModel by viewModel {
        parametersOf(intent.getLongExtra(MANAGED_GROUP_ID_KEY, MANAGED_GROUP_INVALID_ID))
    }

    private val recyclerViewScrollListener = object : RecyclerView.OnScrollListener() {
        private var fabsVisible = true

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            when (newState) {
//                RecyclerView.SCROLL_STATE_IDLE -> {
////                    addTaskButton.show()
////                    editTaskGroupButton.show()
//                }
//                else -> {
//                    addTaskButton.hide()
//                    editTaskGroupButton.hide()
//                }
//            }
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(-1) && !recyclerView.canScrollVertically(1)) {

            }
        }

        private fun showFabs(show: Boolean) {
            if (show) {
                addTaskButton.show()
                editTaskGroupButton.show()
            } else {
                addTaskButton.hide()
                editTaskGroupButton.hide()
            }
        }

    }

    private lateinit var viewBinding: ActivityTaskGroupManageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_group_manage)
        viewBinding.apply {
            viewModel = tasksViewModel
            lifecycleOwner = this@TaskGroupManageActivity
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() = viewBinding.apply {
        linearLayoutManager = LinearLayoutManager(baseContext)
        tasksAdapter = GroupedTasksAdapter()
        tasks.itemAnimator = null
        tasks.addOnScrollListener(recyclerViewScrollListener)
        executePendingBindings()
    }

    companion object {
        const val MANAGED_GROUP_ID_KEY = "group_id"
        const val MANAGED_GROUP_INVALID_ID = -1L
    }
}