package one.gypsy.neatorganizer.domain.dto.tasks

data class SingleTaskGroupEntryDto(
    val id: Long,
    val name: String,
    val tasksDone: Int,
    val tasksCount: Int
)
