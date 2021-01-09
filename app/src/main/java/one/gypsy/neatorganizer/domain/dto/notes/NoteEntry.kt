package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.data.database.entity.notes.NoteEntity

class NoteEntry(
    val id: Long = 0,
    val title: String,
    override val createdAt: Long,
    val color: Int
) : Timestamped

fun NoteEntry.toNoteEntity() = NoteEntity(
    id = id,
    createdAt = createdAt,
    title = title,
    color = color
)
