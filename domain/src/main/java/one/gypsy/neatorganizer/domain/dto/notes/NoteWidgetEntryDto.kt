package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.model.notes.NoteWidgetEntry

data class NoteWidgetEntryDto(
    val widgetId: Int,
    val noteId: Long,
    val color: Int
)

internal fun NoteWidgetEntryDto.toNoteWidget() = NoteWidgetEntry(
    widgetId = widgetId,
    color = color,
    noteId = noteId
)
