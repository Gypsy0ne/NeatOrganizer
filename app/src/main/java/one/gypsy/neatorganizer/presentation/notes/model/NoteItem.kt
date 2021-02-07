package one.gypsy.neatorganizer.presentation.notes.model

import one.gypsy.neatorganizer.domain.dto.notes.Note

data class NoteItem(
    val id: Long,
    val title: String,
    val content: String,
    override val createdAt: Long,
    val color: Int
) : one.gypsy.neatorganizer.database.entity.Timestamped

fun NoteItem.toNote() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    color = color
)
