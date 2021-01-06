package one.gypsy.neatorganizer.presentation.notes.model

import one.gypsy.neatorganizer.domain.dto.notes.Note
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntry

fun NoteEntry.toNoteEntryItem() = NoteEntryItem(id, createdAt, title, color)

fun Note.toNoteItem() = NoteItem(
    id = id,
    content = content,
    title = title,
    createdAt = createdAt,
    color = color
)
