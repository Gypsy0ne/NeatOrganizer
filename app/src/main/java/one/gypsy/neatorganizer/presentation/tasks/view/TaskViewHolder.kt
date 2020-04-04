package one.gypsy.neatorganizer.presentation.tasks.view

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import one.gypsy.neatorganizer.presentation.tasks.model.TaskListItem
import one.gypsy.neatorganizer.utils.LifecycleAware

abstract class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleAware {
    fun onAppear() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun onDisappear() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
     abstract fun bind(data: TaskListItem)
}
