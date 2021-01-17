package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.database.entity.notes.NoteWidgetEntity

data class NoteWidgetEntry(
    val widgetId: Int,
    val noteId: Long,
    val color: Int
)

fun NoteWidgetEntry.toNoteWidgetEntity() =
    NoteWidgetEntity(widgetId = widgetId, color = color, noteId = noteId)
