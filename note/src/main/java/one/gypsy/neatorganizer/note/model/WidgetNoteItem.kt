package one.gypsy.neatorganizer.note.model

internal sealed class WidgetNoteItem(open val id: Long) {
    data class EntryItem(
        val createdAt: Long,
        val color: Int,
        val title: String,
        override val id: Long
    ) : WidgetNoteItem(id = id)

    object FooterItem : WidgetNoteItem(id = FOOTER_ID)

    private companion object {
        const val FOOTER_ID: Long = -1
    }
}
