package one.gypsy.neatorganizer.data.model.notes

import one.gypsy.neatorganizer.database.entity.notes.NoteEntity
import one.gypsy.neatorganizer.database.entity.notes.NoteWidgetEntity
import one.gypsy.neatorganizer.database.entity.notes.WidgetAndNote

internal fun NoteEntity.toNoteEntry() = NoteEntry(
    id = id,
    title = title,
    createdAt = createdAt,
    color = color
)

internal fun NoteEntity.toNote() = Note(
    id = id,
    content = content,
    title = title,
    createdAt = createdAt,
    color = color
)

internal fun NoteWidgetEntity.toNoteWidgetEntry() =
    NoteWidgetEntry(widgetId = widgetId, noteId = noteId, color = color)

internal fun WidgetAndNote.toTitledNoteWidgetEntry() = TitledNoteWidgetEntry(
    appWidgetId = this.widget.widgetId,
    noteId = this.widget.noteId,
    widgetColor = this.widget.color,
    noteTitle = this.note.title,
    noteContent = this.note.content
)
