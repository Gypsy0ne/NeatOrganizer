package one.gypsy.neatorganizer.domain.dto.tasks

data class SingleTaskGroup(val name: String, var id: Long = 0, var tasks: List<SingleTaskEntry>? = null)