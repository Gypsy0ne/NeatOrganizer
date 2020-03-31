package one.gypsy.neatorganizer.binding

import android.content.Context
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import one.gypsy.neatorganizer.R
import one.gypsy.neatorganizer.domain.dto.SingleTaskEntry
import one.gypsy.neatorganizer.presentation.tasks.view.GroupedTaskItem
import one.gypsy.neatorganizer.presentation.tasks.vm.GroupedTaskViewModel

@BindingAdapter("linearGroupedTasksData")
fun setLinearGroupedTasksData(viewGroup: LinearLayout, data: List<SingleTaskEntry>?) {
    data?.forEach {
        viewGroup.addView(
            createGroupedTaskView(viewGroup.context, it),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                viewGroup.context.resources.getDimension(R.dimen.height_item_grouped_task_container)
                    .toInt()
            )
        )
    }
}

private fun createGroupedTaskView(context: Context, groupedTask: SingleTaskEntry) = GroupedTaskItem(context).apply {
    viewModel = GroupedTaskViewModel(groupedTask)
}