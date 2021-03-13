package one.gypsy.neatorganizer.task.model

internal sealed class WidgetTaskGroupItem(open val id: Long) {
    data class Entry(
        override val id: Long,
        val name: String,
        val tasksCount: Int,
        val tasksDone: Int
    ) : WidgetTaskGroupItem(id)

    object Footer : WidgetTaskGroupItem(FOOTER_ID)

    private companion object {
        const val FOOTER_ID: Long = -1
    }
}
