package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.model.notes.NoteEntry

fun NoteEntry.toDto() = NoteEntryDto(
    id = id,
    title = title,
    createdAt = createdAt,
    color = color
)
