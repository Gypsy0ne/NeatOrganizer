package one.gypsy.neatorganizer.binding

import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.domain.dto.SingleTaskGroup
import one.gypsy.neatorganizer.presentation.tasks.view.SingleTaskGroupItem
import one.gypsy.neatorganizer.presentation.tasks.view.SingleTaskItem
import one.gypsy.neatorganizer.presentation.tasks.view.TasksFragment
import one.gypsy.neatorganizer.utils.CollectionUIState

@BindingAdapter("adapterData")
fun <T> setAdapterData(recyclerView: RecyclerView, dataCollection: T?) {
    if (recyclerView.adapter is BindableAdapter<*> && dataCollection != null) {
        (recyclerView.adapter as BindableAdapter<T>).setData(dataCollection)
    }
}

@BindingAdapter(
    value = ["tasksAdapterData", "headerInteractionListener", "itemInteractionListener"],
    requireAll = false
)
fun setTasksAdapterData(
    recyclerView: RecyclerView,
    dataCollection: List<SingleTaskGroup>?,
    headerInteractionListener: TasksFragment.TaskGroupInteractionListener?,
    itemInteractionListener: TasksFragment.SingleTaskInteractionListener?
) {
    dataCollection?.forEach { singleTaskGroup ->
        (recyclerView.adapter as GroupAdapter).add(
            createExpandableTaskGroup(
                singleTaskGroup,
                headerInteractionListener,
                itemInteractionListener
            )
        )
    }
}

private fun createExpandableTaskGroup(
    singleTaskGroup: SingleTaskGroup,
    headerInteractionListener: TasksFragment.TaskGroupInteractionListener?,
    itemInteractionListener: TasksFragment.SingleTaskInteractionListener?
): ExpandableGroup {
    return ExpandableGroup(
        createConfiguredTaskGroup(
            singleTaskGroup,
            headerInteractionListener
        )
    ).apply {
        singleTaskGroup.tasks?.forEach {
            this.add(createConfiguredTaskItem(it, itemInteractionListener))
        }
    }
}

private fun createConfiguredTaskGroup(
    taskGroup: SingleTaskGroup,
    headerInteractionListener: TasksFragment.TaskGroupInteractionListener?
) =
    SingleTaskGroupItem(taskGroup).apply {
        interactionListener = headerInteractionListener
    }

private fun createConfiguredTaskItem(
    taskItem: SingleTaskEntry,
    itemInteractionListener: TasksFragment.SingleTaskInteractionListener?
) =
    SingleTaskItem(taskItem).apply {
        interactionListener = itemInteractionListener
    }


@BindingAdapter(value = ["adapter", "layoutManager"], requireAll = false)
fun setAdapter(
    recyclerView: RecyclerView,
    rvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    rvLayoutManager: RecyclerView.LayoutManager
) {
    recyclerView.apply {
        adapter = rvAdapter
        layoutManager = rvLayoutManager
        setHasFixedSize(true)
        setItemViewCacheSize(resources.getInteger(R.integer.default_recycler_view_cache_size))
    }
}

@BindingAdapter("itemInteractionState")
fun setItemInteractionState(recyclerView: RecyclerView, state: CollectionUIState?) {
    if (state != null) {
        recyclerView.findViewHolderForAdapterPosition(state.itemPosition)?.itemView?.startAnimation(
            when (state) {
                is CollectionUIState.ItemEditionSuccess -> {
                    AnimationUtils.loadAnimation(recyclerView.context, R.anim.item_enlarge)
                }
                is CollectionUIState.ItemEditionFailure -> {
                    AnimationUtils.loadAnimation(recyclerView.context, R.anim.item_enlarge)
                }
            }
        )
    }
}


