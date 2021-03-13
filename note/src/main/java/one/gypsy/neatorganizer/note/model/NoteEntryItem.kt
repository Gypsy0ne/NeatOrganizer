package one.gypsy.neatorganizer.note.model

internal data class NoteEntryItem(
    val createdAt: Long,
    val color: Int,
    val title: String,
    val id: Long
)
