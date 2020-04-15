package one.gypsy.neatorganizer.binding

//@BindingAdapter("linearGroupedTasksData")
//fun setLinearGroupedTasksData(viewGroup: LinearLayout, data: List<SingleTaskEntry>?) {
//    data?.forEach {
//        viewGroup.addView(
//            createGroupedTaskView(viewGroup.context, it),
//            LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                viewGroup.context.resources.getDimension(R.dimen.height_item_grouped_task_container)
//                    .toInt()
//            )
//        )
//    }
//}
//
//private fun createGroupedTaskView(context: Context, groupedTask: SingleTaskEntry) = GroupedTaskItem(context).apply {
//    viewModel = GroupedTaskViewModel(groupedTask)
//}