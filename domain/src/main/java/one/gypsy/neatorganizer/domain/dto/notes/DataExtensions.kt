package one.gypsy.neatorganizer.domain.dto.notes

import one.gypsy.neatorganizer.data.model.notes.Note
import one.gypsy.neatorganizer.data.model.notes.NoteEntry
import one.gypsy.neatorganizer.data.model.notes.NoteWidgetEntry
import one.gypsy.neatorganizer.data.model.notes.TitledNoteWidgetEntry

internal fun NoteEntry.toDto() = NoteEntryDto(
    id = id,
    title = title,
    createdAt = createdAt,
    color = color
)

internal fun NoteWidgetEntry.toDto() = NoteWidgetEntryDto(
    widgetId = widgetId,
    noteId = noteId,
    color = color
)

internal fun TitledNoteWidgetEntry.toDto() = TitledNoteWidgetEntryDto(
    appWidgetId = appWidgetId,
    noteId = noteId,
    widgetColor = widgetColor,
    noteTitle = noteTitle,
    noteContent = noteContent
)

internal fun Note.toDto() = NoteDto(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    color = color
)
