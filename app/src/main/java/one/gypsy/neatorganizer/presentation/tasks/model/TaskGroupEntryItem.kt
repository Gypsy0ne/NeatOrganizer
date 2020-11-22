package one.gypsy.neatorganizer.presentation.tasks.model

data class TaskGroupEntryItem(
    val id: Long,
    val name: String,
    val tasksCount: Int,
    val tasksDone: Int
)