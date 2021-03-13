package one.gypsy.neatorganizer.note.model

import one.gypsy.neatorganizer.domain.dto.notes.NoteDto
import one.gypsy.neatorganizer.domain.dto.notes.NoteEntryDto

internal fun NoteEntryDto.toNoteEntryItem() =
    NoteEntryItem(id = id, createdAt = createdAt, title = title, color = color)

internal fun NoteEntryDto.toEntryItem() =
    WidgetNoteItem.EntryItem(id = id, createdAt = createdAt, title = title, color = color)

internal fun NoteDto.toNoteItem() = NoteItem(
    id = id,
    content = content,
    title = title,
    createdAt = createdAt,
    color = color
)
