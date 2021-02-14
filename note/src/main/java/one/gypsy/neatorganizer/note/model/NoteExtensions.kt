package one.gypsy.neatorganizer.note.model

import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

fun NoteEntry.toNoteEntryItem() =
    NoteEntryItem(id = id, createdAt = createdAt, title = title, color = color)

fun Note.toNoteItem() = NoteItem(
    id = id,
    content = content,
    title = title,
    createdAt = createdAt,
    color = color
)
