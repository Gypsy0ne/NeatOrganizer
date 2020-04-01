package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.binding.Bindable
import one.gypsy.neatorganizer.binding.BindableAdapter
import one.gypsy.neatorganizer.domain.dto.Task
import one.gypsy.neatorganizer.utils.LifecycleAware

class GroupedTasksAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BindableAdapter<List<TaskListItem>> {

    private val tasks = mutableListOf<TaskListItem>()
    override fun setData(dataCollection: List<TaskListItem>) {
        //TODO add diff util here
        tasks.clear()
        tasks.addAll(dataCollection)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TaskViewType.GROUP.value -> {
                TaskGroupViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_task_group,
                        parent,
                        false
                    )
                )
            }
            else -> {
                TaskViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_grouped_task,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        (holder as? LifecycleAware)?.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        (holder as? LifecycleAware)?.markDetach()
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (tasks[position].grouping) {
            TaskViewType.GROUP.value
        } else {
            TaskViewType.ENTRY.value
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(tasks[position].grouping) {
            (holder as TaskGroupViewHolder).bind(tasks[position])
        } else {
            (holder as TaskViewHolder).bind(tasks[position])
        }
    }

    enum class TaskViewType(val value: Int) {
        GROUP(0),
        ENTRY(1)
    }

}
