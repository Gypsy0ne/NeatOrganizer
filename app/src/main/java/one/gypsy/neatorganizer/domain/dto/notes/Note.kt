package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.database.entity.notes.NoteEntity

class Note(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
    val color: Int
)

fun Note.toNoteEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    color = color
)
