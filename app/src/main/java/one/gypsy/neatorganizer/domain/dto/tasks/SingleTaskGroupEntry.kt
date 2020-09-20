package one.gypsy.neatorganizer.domain.dto.tasks

data class SingleTaskGroupEntry(
    val id: Long,
    val name: String,
    val tasksDone: Int,
    val tasksCount: Int
)