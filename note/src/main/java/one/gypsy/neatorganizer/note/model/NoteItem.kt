package one.gypsy.neatorganizer.note.model

import one.gypsy.neatorganizer.domain.dto.notes.Note

data class NoteItem(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
    val color: Int
)

fun NoteItem.toNote() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    color = color
)
