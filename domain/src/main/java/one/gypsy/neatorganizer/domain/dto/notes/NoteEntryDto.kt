package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.model.notes.NoteEntry

class NoteEntryDto(
    val id: Long = 0,
    val title: String,
    val createdAt: Long,
    val color: Int
)

internal fun NoteEntryDto.toNoteEntry() = NoteEntry(
    id = id,
    createdAt = createdAt,
    title = title,
    color = color
)
