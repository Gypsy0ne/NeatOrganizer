package one.gypsy.neatorganizer.presentation.notes.model

import one.gypsy.neatorganizer.data.database.entity.Timestamped
import one.gypsy.neatorganizer.domain.dto.notes.Note

data class NoteItem(
    val id: Long,
    val title: String,
    val content: String,
    override val createdAt: Long
) : Timestamped

fun NoteItem.toNote() = Note(id = id, title = title, content = content, createdAt = createdAt)
