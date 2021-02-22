package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.model.notes.Note

class NoteDto(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
    val color: Int
)

fun NoteDto.toNote() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    color = color
)
