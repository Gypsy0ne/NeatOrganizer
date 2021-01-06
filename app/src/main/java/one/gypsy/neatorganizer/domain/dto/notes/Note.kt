package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.data.database.entity.notes.NoteEntity

class Note(
    val id: Long,
    val title: String,
    val content: String,
    override val createdAt: Long,
    val color: Int
) :
    Timestamped

fun Note.toNoteEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    color = color
)
