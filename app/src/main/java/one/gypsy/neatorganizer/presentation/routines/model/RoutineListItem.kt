package one.gypsy.neatorganizer.presentation.routines.model

sealed class RoutineListItem(
    open val id: Long,
    open val name: String,
    open val edited: Boolean
) {

    data class RoutineListHeader(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        val subItemsCount: Int = 0,
        val expanded: Boolean = false,
        val scheduleDays: List<Boolean>
    ) : RoutineListItem(id = id, name = name, edited = edited)

    data class RoutineListSubItem(
        override val id: Long,
        override val name: String,
        override val edited: Boolean = false,
        val routineId: Long,
        val done: Boolean = false
//        val visible: Boolean = false
    ) : RoutineListItem(id = id, name = name, edited = edited)
}