package one.gypsy.neatorganizer.data.model.notes

import one.gypsy.neatorganizer.database.entity.notes.NoteWidgetEntity

data class NoteWidgetEntry(
    val widgetId: Int,
    val noteId: Long,
    val color: Int
)

fun NoteWidgetEntry.toNoteWidgetEntity() = NoteWidgetEntity(
    widgetId = widgetId,
    color = color,
    noteId = noteId
)
