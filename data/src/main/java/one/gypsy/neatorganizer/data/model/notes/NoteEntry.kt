package one.gypsy.neatorganizer.data.model.notes

import one.gypsy.neatorganizer.database.entity.notes.NoteEntity

class NoteEntry(
    val id: Long = 0,
    val title: String,
    val createdAt: Long,
    val color: Int
)

internal fun NoteEntry.toNoteEntity() = NoteEntity(
    id = id,
    createdAt = createdAt,
    title = title,
    color = color
)
